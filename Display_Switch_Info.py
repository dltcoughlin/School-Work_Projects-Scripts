

from netmiko import ConnectHandler
import csv
def connection(deviceType, ip, username, password):
    net_connect = ConnectHandler(device_type=deviceType, ip=ip, username=username, password=password)
    output = net_connect.send_command("show run")
    return output
f = open("Input.csv", "r")
reader = csv.reader(f, delimiter=",")
x = 0
for i in reader:
    if x == 0:
        pass
        x = 1
    else:
        try:
            output = connection(i[0], i[1], i[2], i[3])
        except Exception as ex:
            output = 'Error'
        with open(str(i[1]) + '_Config.txt', 'w') as f:
            f.write(output)






