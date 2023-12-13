import numpy as np

input = []
with open("Day13/input.txt", "r") as f:
    input = f.readlines()

counter = 0
for karte in input:
    input[counter] = karte.replace("\n", "")
    counter += 1

maps = [[]]
counter = 0
for line in input: 
    if line == "":
        maps.append([])
        counter += 1
        continue
    maps[counter].append(list(line))

# print(np.array(maps[0]).T)
verticalReflectionScore = 0 
moneymoneymoney = 0
horizontalReflectionScore = 0 
rememberXCORD = 0
posColCounter = 0
sum = 0

for map in maps: 
    a = np.array(map)
    maxHorizontal = 0
    maxVertiacal = 0
    # Hier werden die waagerechte Spiegel betrachtet
    posColCounter = 0
    for xCoord in range(a.shape[1]):
        # Nicht erste Position
        if xCoord != 0:
            # Rechtes Symbol == linkes
            if (a[:, xCoord] == a[:, xCoord-1]).all():
                #n solange bis kein übernächstes übereinstimmt
                posColCounter = 1
                for leftCols in range(xCoord-2, 0-1, -1):
                    # 
                    if (xCoord+posColCounter) < a.shape[1]:       
                        if (a[:, leftCols] == a[:, xCoord+posColCounter]).all():
                            if (leftCols == 0 or xCoord+posColCounter == a.shape[1]-1):
                                verticalReflectionScore += xCoord
            #                 # print(f"{a[i]}; {a[i+posColCounter]}")
            #                 moneymoneymoney += 1
            #             if (a[:, leftCols] != a[:, xCoord+posColCounter]).all():
            #                 # print(f"{a[i]}; {a[i+posColCounter]}")
            #                 # print(f"Abbruch")
            #                 # print(f"Vertical Reflection: {verticalReflectionScore}, MoneyMoneyMoney: {moneyMoneyMoney}")
            #                 break
                        posColCounter += 1
            # # verticalReflectionScore += moneymoneymoney
            # if maxVertiacal < moneymoneymoney:
            #     maxVertiacal = moneymoneymoney
                
                
            # moneymoneymoney = 0

    # Hier werden die vertikale Spiegel betrachtet
    posColCounter = 0 
    b = a.T
    for xCoord in range(b.shape[1]):
        # Nicht erste Position
        if xCoord != 0:
            # Rechtes Symbol == linkes
            if (b[:, xCoord] == b[:, xCoord-1]).all():
                # moneymoneymoney = 1
                posColCounter = 1
                # solange bis kein übernächstes übereinstimmt
                for leftCols in range(xCoord-2, 0-1, -1):
                    if (xCoord+posColCounter) < b.shape[1]:        
                        if (b[:, leftCols] == b[:, xCoord+posColCounter]).all():
                            if (leftCols == 0 or xCoord+posColCounter == b.shape[1]-1):
                                horizontalReflectionScore += xCoord
    #                         # print(f"{a[i]}; {a[i+posColCounter]}")
    #                         moneymoneymoney += 1
    #                     if (b[:, leftCols] != b[:, xCoord+posColCounter]).all():
    #                         # print(f"{a[i]}; {a[i+posColCounter]}")
    #                         # print(f"Abbruch")
    #                         # print(f"Vertical Reflection: {verticalReflectionScore}, MoneyMoneyMoney: {moneyMoneyMoney}")
    #                         break
                        posColCounter += 1
            
    #         if maxHorizontal < moneymoneymoney:
    #             maxHorizontal = moneymoneymoney
                
    #         moneymoneymoney = 0
        

    # if (maxHorizontal < maxVertiacal): 
    #     verticalReflectionScore += rememberXCORD 
    # else: 
    #     horizontalReflectionScore += rememberXCORD

    sum = verticalReflectionScore + horizontalReflectionScore*100

    print(verticalReflectionScore + horizontalReflectionScore*100)
print("405")