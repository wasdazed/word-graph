# encoding: utf8

# read ascii-encoded file
import codecs
# multi-delimeter split
import re
# data serialization
import pickle

# TODO: comapre performance to Trie
#from Bio import trie
from collections import defaultdict 

# symbols from sources go in '\xXX' notation, while ones that are read from file are in u\`uXXXX'
vowels = set('еыаоэёяию'.decode('utf8'))

class Orphonode():
    ''' Node of the orphoepic dictionary:
        - id is a unique word id
        - patterns is a set of syllabic patterns
    '''
    def __init__(self, wid, ptrn):
        self.id = wid
        self.patterns = set([ptrn,])

    def add(self, ptrn):
        self.patterns.add(ptrn)


class Orphoepics():
    '''Contains a mapping from words to ids and syllabic patterns. Used to symbolize the text and
        build the syllabic tree
    '''
    def __init__(self, verbose = True):
        # do we print anything
        self.__verbose = verbose
        # word id generator
        self.__id_seq = 0
        # create an empty prefix tree structure
        self.__container = dict()

    # extracts syllable structure from the marked word, stores the word and the syllabic pattern 
    # in the tree
    def store(self, marked_word):
        (word, ptrn) = pattern(marked_word)
        if not self.__container.get(word):
            self.__container[word] = Orphonode(id, ptrn)
        else: 
            self.__container[word].add(ptrn)
        pass

    # returns the word's id
    def id(word):
        return self.__container[word].id

    # returns the word's syllabic pattern 
    def pattern(word):
        return self.__container[word].id


''' Count russian vowels in a str
'''
def count_vowels(word):
    count = 0
    for s in word:
        if s in vowels:
            count += 1
    return count

''' returns a pair (word, pattern), where
    word is a word without the accent sign,
    pattern is a list of 0 (no stress) and 1 (stress), one digit per syllable
'''
def pattern(word):
    pattern = ''
    parts = re.split('\'|`', word)
    for i in range(len(parts)):
        # if it is not a tail, account for a stressed syllable
        if i < len(parts) - 1:
            # the stress is always after the stressed vowel
            pattern += '0' * (count_vowels(parts[i]) - 1) + '1'
        else:
            pattern += '0' * count_vowels(parts[i])
    
    return (''.join(parts), pattern)

def main():
    filename = 'orphoepic.dict'
    # dictionary word -> (id, pattern)
    odict = Orphoepics()

    i = 0
    for line in codecs.open('All_Forms.txt', encoding='cp1251'):
        line = line.strip()
        if line:
            (word, forms) = line.split('#')
            for f in forms.split(','):
                odict.store(f)
                i += 1
                if not i % 10000:
                    print i
        
    with open(filename, 'w') as f:
        print 'storing the dictionary in %s' % filename
        pickle.dump(odict, f)

main()