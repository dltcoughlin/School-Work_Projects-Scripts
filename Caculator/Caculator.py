#Dalton Coughlin
#Project 1
#Simple Caculator
from tkinter import *
import sys
#Grabs numbers out of string
def grabNumbers(numbers):
    grabNumbers = re.compile(r'\d+(?:\.\d+)?')
    returnNumbers = grabNumbers.findall(numbers)
    return (returnNumbers)
#Escalates the grid 
def escalateGrid(rowColumnTrack, gridInfo):
    rowColumnTrack = [rowColumnTrack[0], rowColumnTrack[1]+1]
    if rowColumnTrack[1] >= gridInfo[1]:
         rowColumnTrack = [rowColumnTrack[0]+1, 0]
    elif rowColumnTrack[0] >= gridInfo[0]:
        print('Error: Too Many Rows Being Built')
        exit()
    return rowColumnTrack
def buildWindow(inputList, num, windowCheck, gridInfo, rowColumnTrack):
    newLine = inputList[num].split()
    #Window Generator
    if newLine[0].lower() == 'window':
        gui.title(newLine[1].strip('"'))
        width = grabNumbers(newLine[2])
        height = grabNumbers(newLine[3])
        gui.geometry(str(width[0]) + "x" + str(height[0]))
        buildWindow(inputList, num+1, True, [0,0,0,0], [0,0])
        return
    #Textfield Generator
    elif newLine[0].lower() == 'textfield':
            text = StringVar()
            textField = Entry(gui, textvariable=text)
            length = newLine[1].strip(';')
            rowColumnTrack = [rowColumnTrack[0]+2, 0]
            textField.grid(columnspan=4, ipadx=int(length))
            buildWindow(inputList, num+1, True, gridInfo, rowColumnTrack)
            return
    #Panel Generator
    elif newLine[0].lower() == 'panel':
        #Builds Grid
        if newLine[2][:4].lower() == 'grid':
            gridOne = grabNumbers(newLine[2])
            gridTwo = grabNumbers(newLine[3])
            gridThree = grabNumbers(newLine[4])
            gridFour = grabNumbers(newLine[5])
            gridInfo = [int(gridOne[0]), int(gridTwo[0]), int(gridThree[0]), int(gridFour[0])]
        elif newLine[2][:4].lower() == 'flow':
            gridInfo = 'pack'
        else:
            print ("Error:Not correct format for panel")
            return
        buildWindow(inputList, num+1, True, gridInfo, rowColumnTrack)
    #Button Generator
    elif newLine[0].lower() == 'button':
        button = Button(gui, text=newLine[1].strip(';"'), height=1, width=7)
        if gridInfo == 'pack':
            button.pack()
        #Builds Button and Moves up Column or Row
        else:
            button.grid(row=rowColumnTrack[0], column=rowColumnTrack[1], padx = gridInfo[2], pady = gridInfo[3])
            rowColumnTrack = escalateGrid(rowColumnTrack, gridInfo)
        buildWindow(inputList, num+1, True, gridInfo, rowColumnTrack)
        return
    #Label Generator
    elif newLine[0].lower() == 'label':
        lbl=Label(gui,text=newLine[1].strip(';"'))
        if gridInfo == 'pack':
            lbl.pack()
        else:
            lbl.grid(row=rowColumnTrack[0], column=rowColumnTrack[1], padx = gridInfo[2], pady = gridInfo[3])
            rowColumnTrack = escalateGrid(rowColumnTrack, gridInfo)
        buildWindow(inputList, num+1, True, gridInfo, rowColumnTrack)
    #Checks if window is the first line of the check
    elif newLine[0].lower() != 'window' and windowCheck == False:
        print ('First Label is not Window')
        return
    #builds radio buttons
    elif newLine[0].lower() == "radio":
        for line in newLine[2:]:
            radioButton = Radiobutton(gui, text=line.strip('",;'))
            if gridInfo == 'pack':
                radioButton.pack()
            else:
                radioButton.grid(row=rowColumnTrack[0], column=rowColumnTrack[1], padx = gridInfo[2], pady = gridInfo[3])
                rowColumnTrack = escalateGrid(rowColumnTrack, gridInfo)
        buildWindow(inputList, num+1, True, gridInfo, rowColumnTrack)
        return
    #ends recurison 
    elif newLine[0].strip(';.') == 'End':
        return
    #if no valid option
    else:
        print ('Not a valid option')
        return
    
#main loop
if __name__ == "__main__":
    # create a GUI window
    gui = Tk()
    #reads file
    with open('input.txt') as f:
        inputLines = f.read().splitlines()
    buildWindow(inputLines, 0, False, 0, 0)
    gui.mainloop()

