def binary_search(arr, x):
    low = 0
    high = len(arr) - 1
    mid = 0

    while low <= high:

        mid = (high + low) // 2

        # If x is greater, ignore left half
        if arr[mid] < x:
            low = mid + 1

        # If x is smaller, ignore right half
        elif arr[mid] > x:
            high = mid - 1

        # means x is present at mid
        else:
            return mid

    # If we reach here, then the element was not present
    return -1


lines = []

while True:
    try:
        line = input()
        lines.append(line)
    except EOFError:
        break

priority_sum = 0

for line in lines:
    firstHalf = line[:len(line)//2]
    secondHalf = line[len(line)//2:]

    match = False
    idx = 0

    while not match:
        for j in range(len(secondHalf)):
            if firstHalf[idx] == secondHalf[j]:
                match = True
                if firstHalf[idx].islower():
                    priority_sum += ord(firstHalf[idx]) - 96
                else:
                    priority_sum += ord(firstHalf[idx]) - 38
                break
        idx += 1

print("The sum of priorities of every rucksack is:", priority_sum)

trio_priority_sum = 0

for i in range(0, len(lines), 3):
    rucksack1 = list(dict.fromkeys(sorted(lines[i])))
    rucksack2 = list(dict.fromkeys(sorted(lines[i + 1])))
    rucksack3 = list(dict.fromkeys(sorted(lines[i + 2])))

    for j in range(len(rucksack1)):
        idx = binary_search(rucksack2, rucksack1[j])
        if idx != -1:
            idx = binary_search(rucksack3, rucksack2[idx])
            if idx != -1:
                if rucksack1[j].islower():
                    trio_priority_sum += ord(rucksack1[j]) - 96
                else:
                    trio_priority_sum += ord(rucksack1[j]) - 38
                break

print("The sum of priorities of every elf trio is:", trio_priority_sum)
