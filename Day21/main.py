def aufgabe_1():

    with open(f"Day21/input.txt", "r") as f:
        data = f.readlines()

    map = []
    copymap = []
    for i in data:
        i = list(i.replace("\n", ""))
        map.append(i)
        # print(i)
    copymap = copy_list(map)

    for i in range(64):
        zeilen_counter = 0

        for zeile in map:
            char_counter = 0
            for char in zeile:
                if char == "O" or char == "S":
                    copymap[zeilen_counter][char_counter] = "."
                    try:
                        # if-Statement
                        if map[zeilen_counter - 1][char_counter] == ".":
                            copymap[zeilen_counter - 1][char_counter] = "O"
                    except:
                        pass
                    try:
                        # if-Statement
                        if map[zeilen_counter + 1][char_counter] == ".":
                            copymap[zeilen_counter + 1][char_counter] = "O"
                    except:
                        pass
                    try:
                        # if-Statement
                        if map[zeilen_counter][char_counter - 1] == ".":
                            copymap[zeilen_counter][char_counter - 1] = "O"
                    except:
                        pass
                    try:
                        # if-Statement
                        if map[zeilen_counter][char_counter + 1] == ".":
                            copymap[zeilen_counter][char_counter + 1] = "O"
                    except:
                        pass

                char_counter += 1
            zeilen_counter += 1
        map = copy_list(copymap)

    plot_count = 0
    for zeile in map:
        for char in zeile:
            if char == "O":
                plot_count += 1
    print(plot_count)


def aufgabe_2():
    ORIGINAL_CARD = []

    with open(f"Day21/testinput.txt", "r") as f:
        data = f.readlines()

    for i in data:
        i = list(i.replace("\n", ""))
        ORIGINAL_CARD.append(i)
        # print(i)
    maps: list[list[list[str]]] = [copy_list(ORIGINAL_CARD)]
    copymap: list[list[list[str]]] = copy_list_list(maps)
    ORIGINAL_CARD[int((len(ORIGINAL_CARD) - 1) / 2)][
        int((len(ORIGINAL_CARD) - 1) / 2)
    ] = "."

    # print(ORIGINAL_CARD)
    # print(" ")
    # print(maps)
    # print(" ")
    # print(copymap)

    print(maps[0])

    for i in range(10):
        map_counter = 0
        for map in maps:
            zeilen_counter = 0
            for zeile in map:
                char_counter = 0
                for char in zeile:
                    if char == "O" or char == "S":
                        # print(zeilen_counter)
                        # print(char)

                        copymap[map_counter][zeilen_counter][char_counter] = "."
                        try:
                            # if-Statement
                            if (
                                maps[map_counter][zeilen_counter - 1][char_counter]
                                == "."
                            ):
                                copymap[map_counter][zeilen_counter - 1][
                                    char_counter
                                ] = "O"
                        except:
                            copy_org = copy_list(ORIGINAL_CARD)
                            inner_zeilen_counter = 0
                            for zeile in copy_org:
                                for i in range(
                                    calcAmountOfHorizontalCards(copymap, ORIGINAL_CARD)
                                    - 1
                                ):
                                    for org_char in ORIGINAL_CARD[inner_zeilen_counter]:
                                        copy_org[inner_zeilen_counter].append(org_char)
                                inner_zeilen_counter += 1
                            copymap.insert(0, copy_org)
                            copymap[0][-1][char_counter] = "O"
                        try:
                            # if-Statement
                            if (
                                maps[map_counter][zeilen_counter + 1][char_counter]
                                == "."
                            ):
                                copymap[map_counter][zeilen_counter + 1][
                                    char_counter
                                ] = "O"
                        except:
                            copy_org = copy_list(ORIGINAL_CARD)
                            inner_zeilen_counter = 0
                            for zeile in copy_org:
                                for i in range(
                                    calcAmountOfHorizontalCards(copymap, ORIGINAL_CARD)
                                    - 1
                                ):
                                    for org_char in ORIGINAL_CARD[inner_zeilen_counter]:
                                        copy_org[inner_zeilen_counter].append(org_char)
                                inner_zeilen_counter += 1
                            copymap.append(copy_org)
                            copymap[-1][0][char_counter] = "O"
                        try:
                            # if-Statement
                            if (
                                maps[map_counter][zeilen_counter][char_counter - 1]
                                == "."
                            ):
                                copymap[map_counter][zeilen_counter][
                                    char_counter - 1
                                ] = "O"
                        except:
                            for inner_map in copymap:
                                inner_zeilen_counter = 0
                                for zeile in inner_map:
                                    for char in ORIGINAL_CARD[inner_zeilen_counter][
                                        ::-1
                                    ]:
                                        zeile.insert(0, char)
                            copymap[map_counter][zeilen_counter][
                                len(ORIGINAL_CARD[0]) - 1
                            ] = "O"
                        try:
                            # if-Statement
                            if (
                                maps[map_counter][zeilen_counter][char_counter + 1]
                                == "."
                            ):
                                copymap[map_counter][zeilen_counter][
                                    char_counter + 1
                                ] = "O"
                        except:
                            for inner_map in copymap:
                                inner_zeilen_counter = 0
                                for zeile in inner_map:
                                    for char in ORIGINAL_CARD[inner_zeilen_counter]:
                                        zeile.append(char)
                            copymap[map_counter][zeilen_counter][
                                len(copymap[map_counter][zeilen_counter])
                                - 1
                                - len(ORIGINAL_CARD[0])
                                - 1
                            ] = "O"

                    char_counter += 1
                zeilen_counter += 1
            maps = copy_list_list(copymap)
        map_counter += 1

        for g in map:
            print("".join(g))

    plot_count = 0
    for map in maps:
        for zeile in map:
            for char in zeile:
                if char == "O":
                    plot_count += 1
    for map in maps:
        for zeile in map:
            print("".join(zeile))

    print(plot_count)


def test():
    original = [["a", "b", "c"], ["a", "b", "c"], ["a", "b", "c"], ["a", "b", "c"]]

    cardA = [
        [
            ["c", "b", "a", "a", "b", "c"],
            ["a", "a", "a", "a", "a", "a"],
            ["a", "a", "a", "a", "a", "a"],
            ["a", "a", "a", "a", "a", "a"],
        ],
        [
            ["b", "b", "b", "b", "b", "b"],
            ["b", "b", "b", "b", "b", "b"],
            ["b", "b", "b", "b", "b", "b"],
            ["b", "b", "b", "b", "b", "b"],
        ],
    ]
    amountOfHorizontalCards = calcAmountOfHorizontalCards(cardA, original)
    amountOfVerticalCards = calcAmountOfVerticalCards(cardA, original)
    print(amountOfHorizontalCards)
    print(amountOfVerticalCards)
    # Nach rechts
    for maps in cardA:
        zeilen_counter = 0
        for zeile in maps:
            for char in original[zeilen_counter]:
                zeile.append(char)

    # Nach links
    for maps in cardA:
        zeilen_counter = 0
        for zeile in maps:
            for char in original[zeilen_counter][::-1]:
                zeile.insert(0, char)

    # Nach oben
    copy_org = copy_list(original)
    zeilen_counter = 0
    for zeile in copy_org:
        for i in range(calcAmountOfHorizontalCards(cardA, original) - 1):
            for org_char in original[zeilen_counter]:
                copy_org[zeilen_counter].append(org_char)
        zeilen_counter += 1
    cardA.insert(0, copy_org)

    # Nach unten
    copy_org = copy_list(original)
    zeilen_counter = 0
    for zeile in copy_org:
        for i in range(calcAmountOfHorizontalCards(cardA, original) - 1):
            for org_char in original[zeilen_counter]:
                copy_org[zeilen_counter].append(org_char)
        zeilen_counter += 1
    cardA.append(copy_org)

    for i in cardA:
        for j in i:
            print("".join(j))


def calcAmountOfHorizontalCards(map, originalMap):
    return int(len(map[-1][-1]) / len(originalMap[-1]))


def calcAmountOfVerticalCards(map, originalMap):
    return int(len(originalMap) / len(map))


def copy_list_list(original: list[list[list[str]]]):
    newMap = []
    map_counter: int = 0
    for map in original:
        # print(map)
        newMap.append([])
        for zeile in map:
            # print(zeile)
            newMap[map_counter].append(list("".join(zeile)))
    return newMap


def copy_list(original):
    newMap = []
    for zeile in original:
        newMap.append(list("".join(zeile)))
    return newMap


if __name__ == "__main__":
    # aufgabe_1()
    aufgabe_2()
    # test()
