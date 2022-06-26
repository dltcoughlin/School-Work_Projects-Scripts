from tkinter import *
import os
from tkinter import messagebox
import tkinter as tk
import subprocess

def releaseIPClick():
    si = subprocess.STARTUPINFO()
    si.dwFlags |= subprocess.STARTF_USESHOWWINDOW
    # si.wShowWindow = subprocess.SW_HIDE # default
    subprocess.call('ipconfig /release', startupinfo=si)

def renewIPClick():
    si = subprocess.STARTUPINFO()
    si.dwFlags |= subprocess.STARTF_USESHOWWINDOW
    # si.wShowWindow = subprocess.SW_HIDE # default
    subprocess.call('ipconfig /renew', startupinfo=si)

def ping():
    pingtarget = pingTargetEntry.get()
    text_box.config(state="normal")
    text_box.delete("1.0", "end")
    output = os.popen('ping ' + pingtarget).read()
    text_box.insert('end', output)
    text_box.config(state="disabled")


def refresh():
    # Reset var and delete all old options
    chosenAdapter = variable.get()
    variable.set('')
    w['menu'].delete(0, 'end')
    import subprocess
    si = subprocess.STARTUPINFO()
    si.dwFlags |= subprocess.STARTF_USESHOWWINDOW
    subprocess = subprocess.Popen("ipconfig /all", stdout = subprocess.PIPE, startupinfo=si)
    subprocess_return = str(subprocess.stdout.read())
    splitList = subprocess_return.split("\\n")
    adapterList = []
    lastList = []
    x = 0
    informationDict = {}
    adapterKey = 0
    for item in splitList:
        print (item)
        if "adapter" in item:
            adapterKey = item.replace(":\\r", '')
            adapterList.append(adapterKey)
            informationDict[adapterKey] = []
        elif 'DHCP Server' in item:
            newItem = item.split(':')[1]
            newerItem = 'DHCP Server:' + newItem.replace('\\r','')
            informationDict[adapterKey].append(newerItem)
        elif 'DNS Server' in item:
            newItem = item.split(':')[1]
            newerItem = 'DNS Server:' + newItem.replace('\\r','')
            informationDict[adapterKey].append(newerItem)
        elif "IPv4 Address" in item:
            newItem = item.split(':')[1].replace("r", '')
            newerItem = newItem.replace("\\", '')
            informationDict[adapterKey].append('IPv4:' +newerItem)
            adapterList.append('IP:' + newerItem)
        elif "Subnet mask" in item:
            newItem = item.split(':')[1].replace("r", '')
            newerItem = newItem.replace("\\", '')
            informationDict[adapterKey].append('Subnet Mask:' +newerItem)
        elif "Default Gateway" in item:
            newItem = item.split(':')[1].replace("r", '')
            newerItem = newItem.replace("\\", '')
            informationDict[adapterKey].append('Default Gateway:' +newerItem)
    for item in adapterList:
        if 'IP' in item:
            lastList[x-1] = lastList[x-1] + '--' + item
        else:
            endIndex = (item.rfind("adapter"))
            lastList.append(item[endIndex+8:])
    for choice in lastList:
        w['menu'].add_command(label=choice, command=tk._setit(variable, choice))
    variable.set(chosenAdapter)
    return informationDict
window = Tk()
window.title("Static IP Assignment")
window.geometry('725x600')

staticIpLabel = Label(window, text="Static IP")
staticIpLabel.grid(column=0, row=0)
staticIp = Entry(window,width=25)
staticIp.grid(column=1, row=0)


subnetLabel = Label(window, text="Subnet Mask")
subnetLabel.grid(column=0, row=1)
subnetMask = Entry(window,width=25)
subnetMask.grid(column=1, row=1)
subnetMask.insert(0, "255.255.255.0")


defaultGatewayLabel = Label(window, text="Default Gateway")
defaultGatewayLabel.grid(column=0, row=3)
defaultGateway = Entry(window,width=25)
defaultGateway.grid(column=1, row=3)
defaultGateway.config(state="disabled")

def gwIsChecked():
    if gwcb.get() == 1:
        defaultGateway.config(state="disabled")
    elif gwcb.get() == 0:
        defaultGateway.config(state="normal")

gwcb = IntVar(value = 1)
defaultGatewaychk = Checkbutton(window, text="Disable", onvalue=1, offvalue=0, command=gwIsChecked, variable=gwcb)
defaultGatewaychk.grid(column=2, row=3)


dnsOneLabel = Label(window, text="DNS Primary Server")
dnsOneLabel.grid(column=0, row=5)
dnsOne = Entry(window, width=25)
dnsOne.grid(column=1, row=5)
dnsOne.config(state="disabled")
def dnsOneIsChecked():
    if dnsOneCb.get() == 1:
        dnsOne.config(state="disabled")
    elif dnsOneCb.get() == 0:
        dnsOne.config(state="normal")

dnsOneCb = IntVar(value = 1)
dnsOnechk = Checkbutton(window, text="Disable", onvalue=1, offvalue=0, command=dnsOneIsChecked, variable=dnsOneCb)
dnsOnechk.grid(column=2, row=5)

dnsTwoLabel = Label(window, text="DNS Alternate Server")
dnsTwoLabel.grid(column=0, row=6)
dnsTwo = Entry(window,width=25)
dnsTwo.grid(column=1, row=6)
dnsTwo.config(state="disabled")
def dnsTwoIsChecked():
    if dnsTwoCb.get() == 1:
        dnsTwo.config(state="disabled")
    elif dnsTwoCb.get() == 0:
        dnsTwo.config(state="normal")

dnsTwoCb = IntVar(value = 1)
dnsTwochk = Checkbutton(window, text="Disable", onvalue=1, offvalue=0, command=dnsTwoIsChecked, variable=dnsTwoCb)
dnsTwochk.grid(column=2, row=6)
def dhcpIsChecked():
    if dhcpCb.get() == 1:
        staticIp.config(state="disabled")
        subnetMask.config(state="disabled")
    elif dhcpCb.get() == 0:
        staticIp.config(state="normal")
        subnetMask.config(state="normal")
dhcpCb = IntVar(value = 0)
dhcp = Checkbutton(window, text="IP DHCP", onvalue=1, offvalue=0, command=dhcpIsChecked, variable=dhcpCb)
dhcp.grid(column=0, row=8)

dnsDhcpCb = IntVar(value=0)
dnsDhcp = Checkbutton(window, text="DNS Dhcp", onvalue=1, offvalue=0, variable=dnsDhcpCb)
dnsDhcp.grid(column=0, row=9)

def keepStatic():
    if keepStaticIPCheckBox.get() == 1:
        staticIp.config(state="disabled")
        subnetMask.config(state="disabled")
    elif keepStaticIPCheckBox.get() == 0:
        staticIp.config(state="normal")
        subnetMask.config(state="normal")
keepStaticIPCheckBox = IntVar(value = 0)
keepStatic = Checkbutton(window, text="Keep Static IP", onvalue=1, offvalue=0, command=keepStatic, variable=keepStaticIPCheckBox)
keepStatic.grid(column=0, row=10)

def clicked():
    dnsOneSet = ''
    dnsTwoSet = ''
    defaultGatewaySet = ''
    staticIPSet = staticIp.get()
    subnetMaskSet = subnetMask.get()
    adapter = variable.get()
    if dhcpCb.get() == 1:
        command = 'netsh interface ip set address name="' + adapter.split('--')[0] + '" source=dhcp'
        os.system(command)
        releaseIPClick()
        renewIPClick()
    elif keepStaticIPCheckBox.get() != 1:
        if gwcb.get() == 0:
            defaultGatewaySet = defaultGateway.get()
        if dnsOneCb.get() == 0:
            dnsOneSet = dnsOne.get()
        if dnsTwoCb.get() == 0:
            dnsTwoSet = dnsTwo.get()
        command = 'netsh interface ip set address name = "' + adapter.split('--')[0] + '" static ' + staticIPSet + " " + subnetMaskSet + " " + defaultGatewaySet
        os.system(command)
    if dnsDhcpCb.get() == 1:
        command = 'netsh interface ip set dns "' + adapter.split('--')[0] + '" source=dhcp'
        os.system(command)
        releaseIPClick()
        renewIPClick()
    else:
        if dnsOneSet:
            command = 'netsh interface ip set dns name = "' + adapter.split('--')[0] + '" static ' + dnsOneSet
            os.system(command)
        if dnsTwoSet:
            command = 'netsh interface ip add dnsservers name = "' + adapter.split('--')[0] + '" ' + dnsTwoSet + " index=2"
            os.system(command)
    refresh()

def option_changed(*args):
    pass

btn = Button(window, text="Sumbit", command=clicked)
variable = StringVar(window)
variable.trace("w", option_changed)

def callback(*args):
    staticIp.delete(0, 'end')
    defaultGateway.delete(0, 'end')
    dnsOne.delete(0, 'end')
    dnsTwo.delete(0, 'end')


lastList = [0]
btn.grid(column=1, row=8)
variable = StringVar()
w = OptionMenu(window, variable, *lastList)
variable.trace("w", callback)
w.grid(column=1, row=7)


releaseBtn = Button(window, text="Release IP", command=releaseIPClick)
releaseBtn.grid(column=2, row=9)
renewBtn = Button(window, text="Renew IP", command=renewIPClick)
renewBtn.grid(column=2, row=10)
pingTargetEntry = Entry(window, width=25)
pingTargetEntry.grid(column=1, row=13)
pingBtn = Button(window, text="Ping IP", command=ping)
pingBtn.grid(column=1, row=15)

def moreInfoClick():
    adapter = variable.get()
    informationDict = refresh()
    message = ''
    newWindow = tk.Toplevel(window)
    for item in informationDict:
        if adapter.split('--')[0] in item:
            for text in informationDict[item]:
                message += text + '\n'
            if not message:
                message = 'Not Connected'
    labelExample = tk.Label(newWindow, text=message)
    newWindow.title(adapter.split('--')[0])
    newWindow.geometry('300x150')

    labelExample.pack()
text_box = Text(
    window,
    height=14,
    width=60
)
text_box.grid(column=1, row=17)
outputLabel = Label(window, text="Ping Output")
outputLabel.grid(column=1, row=16)
targetIPLabel = Label(window, text="Destination IP:")
targetIPLabel.grid(column=1, row=12)
moreInfo = Button(window, text="More Adapter Info", command=moreInfoClick)
moreInfo.grid(column=2, row=7)
text_box.config(state="disabled")
refresh()
window.mainloop()
