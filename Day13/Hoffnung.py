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

verticalReflectionScore = 0 
moneymoneymoney = 0
horizontalReflectionScore = 0 
rememberXCORD = 0
posColCounter = 0
vorherigesHorizontal = 0 
vorherigesVertikal = 0 
sum = 0


runde = 0 
# Durch jede Map iterieren 
for map in maps: 
    erbenisH = False
    ergebnisV = False
    a = np.array(map)
    maxHorizontal = 0
    maxVertiacal = 0
    posColCounter = 0
    #print(a)
    # Durch jeden Char einer Row iterieren
    if (a[:, 1] == a[:, 1-1]).all():
        vorherigesVertikal = verticalReflectionScore
        verticalReflectionScore += 1
        rememberXCORD = 1
        ergebnisV = True
    elif (a[:, a.shape[1]-1] == a[:, a.shape[1]-2]).all(): 
        vorherigesVertikal = verticalReflectionScore
        verticalReflectionScore += a.shape[1]-1
        rememberXCORD = a.shape[1]-1
        ergebnisV = True
    if (ergebnisV != True):
        for xCoord in range(a.shape[1]):
            # Nicht der Anfang
            if xCoord != 0:
                # Initialer Spiegel (VERTIKALER SPIEGEL)
                if (a[:, xCoord] == a[:, xCoord-1]).all():
                    posColCounter = 1
                    for leftCols in range(xCoord-2, 0-1, -1):
                        #print(leftCols)
                        # 
                        # Nicht das Ende der Row erreicht
                        if (xCoord+posColCounter) < a.shape[1]:      
                            # Die nächsten ... identisch
                            if (a[:, leftCols] == a[:, xCoord+posColCounter]).all():
                                # Nur abschnittene Spiegel verrechnen
                                if ((leftCols == 0) or (xCoord+posColCounter == a.shape[1]-1)):
                                    vorherigesVertikal = verticalReflectionScore
                                    verticalReflectionScore += xCoord
                                    rememberXCORD = xCoord
                                    ergebnisV = True
                            posColCounter += 1
    if (ergebnisV != True): 
        posColCounter = 0 
        b = a.T
        #print(b)
        if (b[:, 1] == b[:, 1-1]).all():
            vorherigesHorizontal = horizontalReflectionScore
            horizontalReflectionScore += 1
            rememberXCORD = 1
            erbenisH = True
        elif (b[:, b.shape[1]-1] == b[:, b.shape[1]-2]).all(): 
            vorherigesHorizontal = horizontalReflectionScore
            horizontalReflectionScore += b.shape[1]-1
            rememberXCORD = b.shape[1]-1
            erbenisH = True
        
        if (erbenisH != True):
            for xCoord in range(b.shape[1]):
                if xCoord != 0:
                    if (b[:, xCoord] == b[:, xCoord-1]).all():
                        posColCounter = 1
                        for leftCols in range(xCoord-2, 0-1, -1):
                            if (xCoord+posColCounter) < b.shape[1]:        
                                if (b[:, leftCols] == b[:, xCoord+posColCounter]).all():
                                    if ((leftCols == 0) or (xCoord+posColCounter == b.shape[1]-1)):
                                        vorherigesHorizontal = horizontalReflectionScore
                                        horizontalReflectionScore += xCoord
                                        rememberXCORD = xCoord
                                        erbenisH = True
                                    posColCounter += 1

    if (erbenisH == True and ergebnisV == True):
        print("Hupssi")
    elif (erbenisH == False and ergebnisV == False):
        print("HupssiHUudhgjkahfjkadgjkfsdhohsgo")
        print(f"Map: {runde}")
        print(a)
    if ergebnisV == True:
        print("------------------------------------------------")
        print(f"Runde: {runde}")
        print(f"Vertikaler Spiegel auf xCord:{rememberXCORD}")
        print(f"Vertikales Ergebnis: {vorherigesVertikal} => {verticalReflectionScore}")
    elif erbenisH == True:
        print("------------------------------------------------")
        print(f"Runde: {runde}")
        print(f"Horizontaler Spiegel auf xCord:{rememberXCORD}")
        print(f"Horizotales Ergebnis: {vorherigesHorizontal} => {horizontalReflectionScore}")
    print(f"Summe: {verticalReflectionScore +horizontalReflectionScore*100}")
    runde += 1
#print(sum)