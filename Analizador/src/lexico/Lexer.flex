package lexico;
import static lexico.Tokens.*;

%%
%class Lexer
%type Tokens

ComentBlo = "/*" ( [^*] | \*+ [^/*] )* "*"+ "/"

%{
    public String lexeme;
%}
%%
";"                                 { lexeme=yytext(); return ENDLINE; }
"#"                                 { lexeme=yytext(); return SEPARADOR; }
"="                                 { lexeme=yytext(); return SIGNOIGUAL; }
":"                                 { lexeme=yytext(); return DOSPUNTOS; }
","                                 { lexeme=yytext(); return COMA; }
"-"                                 { lexeme=yytext(); return SIGRESTA; }
"+"                                 { lexeme=yytext(); return SIGSUMA; }
"*"                                 { lexeme=yytext(); return SIGMULTIPLICACION; }
"/"                                 { lexeme=yytext(); return SIGDIVISION; }
"%"                                 { lexeme=yytext(); return SIGMODULO; }
"^"                                 { lexeme=yytext(); return SIGPOTENCIA; }
"&"                                 { lexeme=yytext(); return CONGUNCION; }
"|"                                 { lexeme=yytext(); return DISYUNSION; }
"!"                                 { lexeme=yytext(); return NEGACION; }
"++"                                { lexeme=yytext(); return AUMENTAR; }
"--"                                { lexeme=yytext(); return DISMINUIR; }
"<"                                 { lexeme=yytext(); return MENOR; }
"<="                                { lexeme=yytext(); return MENORIGUAL; }
">"                                 { lexeme=yytext(); return MAYOR; }
">="                                { lexeme=yytext(); return MAYORIGUAL; }
"=="                                { lexeme=yytext(); return IGUAL; }
"!="                                { lexeme=yytext(); return DIFERENTE; }
"("                                 { lexeme=yytext(); return ABREPARENTESIS; }
")"                                 { lexeme=yytext(); return CIERRAPARENTESIS; }
"{"                                 { lexeme=yytext(); return ABRELLAVE; }
"}"                                 { lexeme=yytext(); return CIERRALLAVE; }
"\r"                                { lexeme=yytext(); }
" "                                 { lexeme=yytext(); }

"True"                              { lexeme=yytext(); return LITTRUE; }
"False"                             { lexeme=yytext(); return LITFALSE; }
"int"                               { lexeme=yytext(); return TIPOINT; }
"float"                             { lexeme=yytext(); return TIPOFlOAT; }
"bool"                              { lexeme=yytext(); return TIPOBOOL; }
"char"                              { lexeme=yytext(); return TIPOCHAR; }
"string"                            { lexeme=yytext(); return TIPOSTRING; }
"read"                              { lexeme=yytext(); return RESERVREAD; }
"write"                             { lexeme=yytext(); return RESERVWRITE; }
"if"                                { lexeme=yytext(); return RESERVIF; }
"else"                              { lexeme=yytext(); return RESERVELSE; }
"switch"                            { lexeme=yytext(); return RESERVSWITCH; }
"while"                             { lexeme=yytext(); return RESERVWHILE; }
"forRange"                          { lexeme=yytext(); return RESERVFORRANGE; }
"main"                              { lexeme=yytext(); return RESERVMAIN; }
"break"                             { lexeme=yytext(); return RESERVBREAK; }
"return"                            { lexeme=yytext(); return RESERVRETURN; }
"case"                              { lexeme=yytext(); return RESERVCASE; }
"default"                           { lexeme=yytext(); return RESERVDEFAULT; }
(-?[1-9][0-9]*) | 0                 { lexeme=yytext(); return LITINT; }
-?(([1-9][0-9]*)|0)\.[0-9][1-9]*    { lexeme=yytext(); return LITFLOAT; }
\'[^']\'                            { lexeme=yytext(); return LITCHAR; }
\"([^\"\\]|\\.)*\"                  { lexeme=yytext(); return LITSTRING; }
[a-zA-Z][a-zA-Z0-9\_]*              { lexeme=yytext(); return IDENTIFICADOR; }
"//".*                              { lexeme=yytext(); return COMENTARIO;}
"\n"                                { lexeme=yytext(); return SALTOLINEA; }

{ComentBlo}                         {return COMENTARIOBLOQUE;}

[^]                                   { return ERROR; }