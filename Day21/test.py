liste = ["a", "b", "c"]
liste_copy = ["a", "b", "c"]
for char in liste[::-1]:
    liste_copy.insert(0, char)
print(liste_copy)
