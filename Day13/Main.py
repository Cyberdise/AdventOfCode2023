maps = []
with open("Day13/input.txt", "r") as f:
    maps = f.readlines()

counter = 0
for karte in maps:
    maps[counter] = karte.replace("\n", "")
    counter += 1

xCoord = 0
yCoord = 0
verticalReflectionScore = 0
horizontalReflectionScore = 0
moneyMoneyMoney = 0
moneyMoneyMoneyMoney = 0
for line in maps:
    if line == "":
        xCoord = 0
        yCoord = 0
        continue
    print(line)
    xCoord = 0
    for char in line:
        if xCoord != 0:
            # falls der vorherige char der selbe ist wie der jetzige
            if line[xCoord-1] == char:
                # dann iterier nach links (maximal so oft wie xCoord-1 groß ist) und auch gleichzeitig nach rechts (maximal so oft wie die Länge der Line)
                posColCounter = 1
                moneyMoneyMoney = 0
                for i in range(xCoord-2, 0-1, -1):
                    if (xCoord+posColCounter) < len(line):        
                        if line[i] == line[xCoord+posColCounter]:
                            # print(f"{line[i]}; {line[xCoord+posColCounter]}")
                            moneyMoneyMoney += 1
                        if line[i] != line[xCoord+posColCounter]:
                            # print(f"{line[i]}; {line[xCoord+posColCounter]}")
                            # print(f"Abbruch")
                            # print(f"Vertical Reflection: {verticalReflectionScore}, MoneyMoneyMoney: {moneyMoneyMoney}")
                            break
                        posColCounter += 1
            verticalReflectionScore += moneyMoneyMoney
            moneyMoneyMoney = 0
        xCoord += 1
    
    if yCoord != 0:
        if maps[yCoord-1] == line:
            posRowCounter = 1
            moneyMoneyMoneyMoney = 0
            for i in range(yCoord-2, 0-1, -1):
                if maps[i] == maps[yCoord+posRowCounter]:
                    moneyMoneyMoneyMoney += 1
                if maps[i] != maps[yCoord+posRowCounter]:
                    break
                posRowCounter += 1
        horizontalReflectionScore += moneyMoneyMoneyMoney
        moneyMoneyMoneyMoney = 0
    
    yCoord += 1
print(sum)
print(horizontalReflectionScore*100)