from typing import Final, List, Optional
from dataclasses import dataclass

digits:Final[tuple]  = (0,1,2,3,4,5,6,7,8,9)

@dataclass(init = False, frozen = True)
class TokenType:
    LET:str = 'LET'
    DATA:str = 'DATA'
    READ:str = 'READ'
    RESTORE:str = 'RESTORE'
    DIM:str = 'DIM'

    IF:str = 'IF'
    THEN:str = 'THEN'
    ELSE:str = 'ELSE'
    FOR:str = 'FOR'
    TO:str = 'TO'
    STEP:str = 'STEP'
    NEXT:str = 'NEXT'
    WHILE:str = 'WHILE'
    WEND:str = 'WEND'
    REPEAT:str = 'REPEAT'
    UNTIL:str = 'UNTIL'
    DO:str = 'DO'
    LOOP:str = 'LOOP'
    GOTO:str = 'GOTO'
    ON:str = 'ON'
    GOSUB:str = 'GOSUB'

    DEF:str = 'DEF'
    FN:str = 'FN'

    LIST:str = 'LIST'
    PRINT:str = 'PRINT'
    INPUT:str = 'INPUT'
    TAB:str = 'TAB'
    SPC:str = 'SPC'

    REM:str = 'REM'
    USR:str = 'USR'
    CALL:str = 'CALL'
    TRON:str = 'TRON'
    TROFF:str = 'TROFF'
    ASM:str = 'ASM'

    ABS:str = 'ABS'
    ATN:str = 'ATN'
    COS:str = 'COS'
    EXP:str = 'EXP'
    LOG:str = 'LOG'
    RND:str = 'RND'
    SIN:str = 'SIN'
    TAN:str = 'TAN'
    SQR:str = 'SQR'

    INT:str = 'INT'
    FLOAT:str = 'FLOAT'

class TokenClass:
    def __init__(self, type:TokenType, value:object = None) -> None:
        self.type = type
        self.value = value
    
    def __str__(self) -> str:
        return f'{self.type}:{self.value}' if self.value else f'{self.type}'


class ErrorHandler:
    ...

class Position:
    def __init__(self, index:int, line:int, col:int) -> None:
        ...


class Scanner:
    m_token: List[TokenType] = []
    m_current:int = 0
    
    def __init__(self, source:str) -> None:
        ...
    
    def scan(self) -> None:
        ...

    def get_token(self) -> List[TokenType]: 
        ...
    
    def add_token(self, type:TokenType, token:Optional[str] = None) -> None: 
        ...

    def advance(self) -> None: 
        ...
    ...

if __name__ == '__main__':
    ...