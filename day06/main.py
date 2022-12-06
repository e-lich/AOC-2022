line = input()

list4 = []
list14 = []

found4 = False

for i in range(len(line)):

    if not found4:
        if line[i] in list4:
            list4 = list4[list4.index(line[i])+1:]

        list4.append(line[i])
        if len(list4) == 4:
            print("The first start-of-packet marker is after character:", i + 1)
            found4 = True

    if line[i] in list14:
        list14 = list14[list14.index(line[i])+1:]

    list14.append(line[i])
    if len(list14) == 14:
        print("The first start-of-message marker is after character:", i + 1)
        break

