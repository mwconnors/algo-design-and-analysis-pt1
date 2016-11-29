'''
Created on Jul 16, 2016
'''

def merge(left, right):
    merged = []
    invCount = 0
    i = j = 0
    for k in range( len(left)+len(right) ):
        if i < len(left) and j < len(right):
            if left[i] < right[j]:
                merged.append(left[i]) 
                i += 1
            elif right[j] < left[i]:
                merged.append(right[j])
                j += 1
                invCount += len(left) - i
        elif i == len(left):
            merged.append(right[j])
            j += 1
        elif j == len(right):
            merged.append(left[i])
            i += 1
    return merged, invCount
        
def split_list(a):
    half = len(a)/2;
    return a[:half],a[half:]

def merge_sort(data):
    n = len(data)
    invCount = 0
    if n == 1: return data, invCount;
    left, right = split_list(data)
    a, x = merge_sort(left)
    b, y = merge_sort(right)
    c, z = merge(a, b) 
    return c, x + y + z
    
with open("output_coursera5.txt") as f:
    unordered = [int(line) for line in f]
    length = len(unordered)
    ordered, count = merge_sort(unordered)
    print count