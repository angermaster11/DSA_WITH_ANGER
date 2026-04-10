# 📚 Arrays - Easy Questions Notes (Hinglish)

> Ye notes **anger** ne khud ke liye banaye hain — concepts simple language mein, taaki similar questions bhi aaye toh solve kar sakein! 💪

---

## 📋 Table of Contents
1. [Largest Element in Array](#1-largest-element-in-array)
2. [Second Largest Element](#2-second-largest-element)
3. [Remove Duplicates from Sorted Array](#3-remove-duplicates-from-sorted-array)
4. [Check if Array is Sorted and Rotated](#4-check-if-array-is-sorted-and-rotated)
5. [Left Rotate / Rotate Array by K Steps](#5-left-rotate--rotate-array-by-k-steps)
6. [Max Consecutive Ones](#6-max-consecutive-ones)
7. [Move Zeroes](#7-move-zeroes)
8. [Single Number](#8-single-number)

---

## 1. Largest Element in Array

### 🔶 Problem
Array mein sabse bada element dhundho.

### 💡 Concept (Sochne ka tarika)
- Ek `max` variable le aur usmein `arr[0]` daal do.
- Poore array ko ek baar loop karo.
- Agar koi element `max` se bada mila, toh `max` update kar do.
- Loop khatam hone ke baad `max` hi answer hai.

### 🧠 Key Insight
> **Ek baar traverse karo, bas ek variable track karo.** Yahi pattern bahut saare "find max/min" wale questions mein kaam aata hai.

### ✅ Code Pattern
```java
int max = arr[0];
for (int x : arr) {
    if (x > max) max = x;
}
return max;
```

### 🔁 Similar Questions jahan ye pattern kaam karega:
- Find minimum element
- Find max product of two elements
- Range of array (max - min)

---

## 2. Second Largest Element

### 🔶 Problem
Array mein doosra sabse bada **unique** element dhundho. Agar exist nahi karta toh `-1` return karo.

### 💡 Concept (Sochne ka tarika)
- **Do variables** rakho: `first` (sabse bada) aur `second` (doosra sabse bada).
- Dono ko `Integer.MIN_VALUE` se initialize karo (taaki koi bhi element inse bada ho sakta ho).
- Loop mein:
  - Agar `x > first` → pehle `second = first`, phir `first = x` (**order matter karta hai!**)
  - Warna agar `x > second` **aur** `x != first` → `second = x`
- `x != first` condition isliye zaroori hai taaki same value dobara count na ho.

### 🧠 Key Insight
> **Condition ka order bilkul sahi hona chahiye.** Pehle `first` check karo, tabhi `second` update hoga. Agar ulta karo toh bug aa jayega.

### ✅ Code Pattern
```java
int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
for (int x : arr) {
    if (x > first) {
        second = first;
        first = x;
    } else if (x > second && x != first) {
        second = x;
    }
}
return second == Integer.MIN_VALUE ? -1 : second;
```

### 🔁 Similar Questions:
- Kth largest element (same logic, `k` variables ya sorting)
- Third largest element

---

## 3. Remove Duplicates from Sorted Array

### 🔶 Problem
**Sorted** array se duplicates remove karo **in-place** aur unique elements ki count return karo.

### 💡 Concept — Two Pointer Technique
- **`i`** = slow pointer → unique elements ka last index track karta hai.
- **`j`** = fast pointer → array traverse karta hai.
- Agar `nums[j] != nums[i]` matlab naya unique element mila:
  - `i++` karo
  - `nums[i] = nums[j]` (unique element ko aage shift karo)
- Answer hai `i + 1` (count of unique elements).

### 🧠 Key Insight
> Array **sorted** hai isliye duplicate elements hamesha adjacent hote hain. Two pointer isi cheez ka fayda uthata hai — sirf adjacent compare karo!

### ✅ Code Pattern
```java
int i = 0;
for (int j = 1; j < nums.length; j++) {
    if (nums[j] != nums[i]) {
        i++;
        nums[i] = nums[j];
    }
}
return i + 1;
```

### 🔁 Similar Questions:
- Remove duplicates allowing at most 2 occurrences (same idea, `i >= 2 && nums[j] == nums[i-2]` check)
- Remove specific element from array (same two pointer)

---

## 4. Check if Array is Sorted and Rotated

### 🔶 Problem
Array pehle sorted tha, phir usse rotate kiya gaya. Check karo ki kya ye possible hai.

### 💡 Concept (Sochne ka tarika)
Ek **sorted aur rotated** array mein **sirf ek jagah** par "dip" hogi — yaani ek jagah par `nums[i] < nums[i-1]` hoga. Har jagah array increase karta rahega, bas ek mod point hoga.

**Trick:**
- Count karo kitni baar `nums[i] < nums[i-1]` hua (ye "break points" hain).
- Ek extra check: `nums[last] > nums[0]` — agar last element first se bada hai, toh wrap-around bhi ek break point count hoga.
- Agar total breaks `<= 1` → array sorted + rotated tha ✅

### 🧠 Key Insight
> Sorted rotated array mein **max ek break point** hota hai. Do ya zyada break points matlab array sorted + rotated nahi tha.

```
[3, 4, 5, 1, 2]
         ↑
    Yahan break hai (5→1), count = 1 ✅

[2, 1, 3, 4]
   ↑
Yahan break (2→1), aur last(4) > first(2) → count = 2 ❌
```

### ✅ Code Pattern
```java
int c = 0;
for (int i = 1; i < nums.length; i++) {
    if (nums[i] < nums[i-1]) c++;
}
if (nums[nums.length-1] > nums[0]) c++;
return c <= 1;
```

### 🔁 Similar Questions:
- Find minimum in rotated sorted array
- Search in rotated sorted array
- Check if array is sorted (bas c == 0 check karo)

---

## 5. Left Rotate / Rotate Array by K Steps

### 🔶 Problem
Array ko **K steps right** rotate karo (in-place, O(1) space).

### 💡 Concept — Reverse Trick 🔥
Ye ek **classic trick** hai jise yaad rakhna chahiye:

Array `[1,2,3,4,5,6,7]`, k=3 ke liye answer `[5,6,7,1,2,3,4]` hai.

**3 reversals karo:**
1. Pehle `n-k` elements reverse karo: `[4,3,2,1,5,6,7]`
2. Baaki `k` elements reverse karo: `[4,3,2,1,7,6,5]`
3. Poora array reverse karo: `[5,6,7,1,2,3,4]` ✅

**`k = k % n`** pehle karo — agar k > n hai toh redundant rotations avoid ho jaayein.

### 🧠 Key Insight
> **Reverse trick = O(n) time, O(1) space.** Ye trick left rotation ke liye bhi kaam karti hai — bas reversal ranges badlo.

### ✅ Code Pattern
```java
k = k % n;
reverse(nums, 0, n-k-1);   // Step 1: pehla part
reverse(nums, n-k, n-1);   // Step 2: doosra part
reverse(nums, 0, n-1);     // Step 3: poora array

// Helper:
void reverse(int[] nums, int i, int j) {
    while (i < j) {
        int temp = nums[i];
        nums[i++] = nums[j];
        nums[j--] = temp;
    }
}
```

### 🔁 Similar Questions:
- Left rotate array by k (ranges thodi alag hongi)
- Rotate string
- Reverse words in a string

---

## 6. Max Consecutive Ones

### 🔶 Problem
Binary array mein maximum consecutive `1`s dhundho.

### 💡 Concept (Sochne ka tarika)
- Ek **counter `c`** rakho current streak ke liye.
- Ek **`max`** variable rakho best streak ke liye.
- Loop karo:
  - `1` mila → `c++`
  - `0` mila → `max = Math.max(max, c)` update karo, phir `c = 0` reset karo
- **Loop ke baad** ek baar aur `Math.max(max, c)` return karo — kyunki agar array `1`s pe khatam ho toh last streak count nahi hogi!

### 🧠 Key Insight
> **Loop ke baad bhi max update karna mat bhoolo!** Ye ek common mistake hai. Agar array `[1,1,1]` hai, toh loop mein `0` kabhi nahi aayega, isliye `max` update nahi hoga.

### ✅ Code Pattern
```java
int c = 0, max = 0;
for (int i = 0; i < nums.length; i++) {
    if (nums[i] == 1) {
        c++;
    } else {
        max = Math.max(max, c);
        c = 0;
    }
}
return Math.max(max, c); // ← ye line ZAROOR chahiye!
```

### 🔁 Similar Questions:
- Max consecutive ones III (k zeros flip kar sakte ho — sliding window)
- Longest subarray with only 1s

---

## 7. Move Zeroes

### 🔶 Problem
Array ke saare `0`s end mein move karo, **order maintain** karte hue, **in-place**.

### 💡 Concept — Two Pointer (Write Pointer)
- **`i`** = "write pointer" — yaani yahan tak non-zero elements likh chuke hain.
- **`j`** = "read pointer" — poora array scan karta hai.
- Agar `nums[j] != 0` → `nums[i] = nums[j]` karo aur `i++`.
- Loop khatam hone ke baad, `i` se `n-1` tak saab `0` kar do.

### 🧠 Key Insight
> **Pehle saare non-zero elements aage laao, phir baki jagah 0 bhar do.** Ye Remove Duplicates jaisi hi trick hai — same two pointer pattern!

```
[0, 1, 0, 3, 12]
 ↑j → 0 skip
    ↑j → 1 hai, nums[0]=1, i=1
       ↑j → 0 skip
          ↑j → 3 hai, nums[1]=3, i=2
             ↑j → 12 hai, nums[2]=12, i=3
Phir index 3,4 ko 0 karo → [1, 3, 12, 0, 0] ✅
```

### ✅ Code Pattern
```java
int i = 0;
for (int j = 0; j < nums.length; j++) {
    if (nums[j] != 0) {
        nums[i++] = nums[j];
    }
}
while (i < nums.length) {
    nums[i++] = 0;
}
```

### 🔁 Similar Questions:
- Remove element (same pattern, just different value remove karo)
- Move negative numbers to end
- Segregate 0s and 1s

---

## 8. Single Number

### 🔶 Problem
Array mein ek element ko chhod ke baaki sab **do baar** aate hain. Wo ek element dhundho. **O(n) time, O(1) space.**

### 💡 Concept — XOR Magic 🪄
XOR ke do magical properties:
1. `a ^ a = 0` (same numbers XOR karo toh 0 milta hai)
2. `a ^ 0 = a` (koi bhi number 0 se XOR karo toh wahi milta hai)

Toh agar sab numbers XOR karo:
```
[4, 1, 2, 1, 2]
4 ^ 1 ^ 2 ^ 1 ^ 2
= 4 ^ (1^1) ^ (2^2)
= 4 ^ 0 ^ 0
= 4 ✅
```
Pairs cancel out ho jaate hain, sirf single number bachta hai!

### 🧠 Key Insight
> **XOR = pairs cancel, single bachta hai.** Ye trick tab bhi kaam karti hai jab saare elements ek number ko chhod ke **odd times** aayein.

### ✅ Code Pattern
```java
int result = 0;
for (int x : nums) {
    result ^= x;
}
return result;
```

### 🔁 Similar Questions:
- Single Number II (every element appears 3 times except one — bit manipulation)
- Single Number III (do elements ek baar aate hain)
- Find missing number (XOR trick se)
- Find the element that appears odd number of times

---

## 🗂️ Patterns Summary — Quick Revision

| Pattern | Questions |
|---|---|
| **Simple Traversal (max/min track)** | Largest Element, Second Largest |
| **Two Pointer (slow-fast)** | Remove Duplicates, Move Zeroes |
| **Reverse Trick** | Rotate Array by K |
| **Break Point Count** | Sorted & Rotated Check |
| **Counter + Reset** | Max Consecutive Ones |
| **XOR** | Single Number |

---

## 🧩 General Tips

1. **Array sorted hai?** → Two pointer ya binary search socho.
2. **In-place chahiye, O(1) space?** → Two pointer ya reverse trick socho.
3. **Pairs cancel karne hain?** → XOR socho.
4. **Streak track karni hai?** → Counter + reset pattern + loop ke baad bhi check.
5. **`k % n`** hamesha karo jab rotation wala question ho — redundancy avoid karta hai.
