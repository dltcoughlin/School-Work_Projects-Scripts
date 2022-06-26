import pyodbc
import paramiko

# def executeCommand(command):
#     cursor.execute(command)
# connection = pyodbc.connect(driver='{SQL Server}',
#                                 server='localhost\SQLEXPRESS', database='TestDataBase',
#                                 trusted_connection='yes',autocommit= True)
# backupLoc = 'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\Backup\TestDatabase.BAK'
# command= "BACKUP DATABASE TestDataBase TO DISK = N'{0}'".format(backupLoc)
# cursor = connection.cursor()
# executeCommand(command)


host = "sftp.h4-technology.com"
port = 22
transport = paramiko.Transport((host, port))
username = "CCTCFin"
password = "39numeral40fair6flow5097"
transport.connect(username=username, password=password)
sftp = paramiko.SFTPClient.from_transport(transport)
path = "/incoming/test.txt"
localPath = "test.txt"
sftp.put(localPath, path)
sftp.close()
transport.close()





