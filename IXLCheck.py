from csv import reader
import csv

inactiveGraduated = []
activeList = []

with open('IXL.csv') as f:
    csv_reader = reader(f)
    ixlList = list(csv_reader)

with open('studentReport.csv') as f:
    csv_reader = reader(f)
    studentList = list(csv_reader)

    
for item in ixlList:
    x = 0
    for item1 in studentList:
        if item[2].replace(" ",'') == item1[1].replace(" ",''):
                if item1[6] == 'Active':
                    if item in activeList:
                        pass
                    else:
                        activeList.append(item1)
                else:
                    if item in inactiveGraduated:
                        pass
                    else:
                        inactiveGraduated.append(item1)
                x=1
                break
    if x == 0:
        inactiveGraduated.append(item)

with open("output.csv", 'w', newline="")as f:
    writer = csv.writer(f)
    for item in inactiveGraduated:
        writer.writerow(item)
        
