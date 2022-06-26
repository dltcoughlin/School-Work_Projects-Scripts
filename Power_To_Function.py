x=0
for i in range(30,300,15):
   f=(800*pow(i,2))+(12*pow(i,2))+1500
   g=3*pow(i,3)
   print(i," ",f," ",g)
   if g > f:
       print ('g overtakes f')
       break
   
