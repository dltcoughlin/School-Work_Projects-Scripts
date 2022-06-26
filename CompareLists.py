from csv import reader
with open('pharm_list.csv') as f:
    csv_reader = reader(f)
    pharmList = list(csv_reader)

with open('student1.csv') as f:
    csv_reader = reader(f)
    studentList = list(csv_reader)

for item in pharmList:
    for item1 in studentList:
        if item[0].lower() == item1[0].lower():
            print (item)
