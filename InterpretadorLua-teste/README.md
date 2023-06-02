# InterpretadorLUA
o main se localiza na pasta plp
codigo em lua utilizado para testes:

string, int, bol, double ='lol', -2,true, 1.5
t= 25*2
x= double*4.0*double*2.0
y= int+2
z= -2.2-double
w= 2.3/2.3 
a=0  
print('teste de string:')
print(string)
print('teste de int:')
print(int)
print('teste de bolean:')
print(bol)
print('teste de double:')
print(double)
print('teste de multiplicacao inteiros:')
print(t)
print('teste de multiplicacao double:')
print(x)
print('teste de soma inteiros:')
print(y)
print('teste de subtracao double:')
print(z)
print('teste de divisao double:')
print(w)
print('teste de entrada e estrutura de repeticao:')
print("digite ate onde o contador deve contar")
b=io.read()
while a<b do
    a=a+1
    print(a)
end
print('teste de soma na saida:')
a=0 
while a<b do
    a=a+1
    print(a+a)
end
