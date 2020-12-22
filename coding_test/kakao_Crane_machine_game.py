def solution(board, moves):
    answer = 0
    box = []
    for moves in moves:
        for level in range(len(board)):
            if board[level][moves-1] != 0:
                box.append(board[level][moves-1])
                board[level][moves-1] = 0
                if len(box) > 1:
                    if (box[-1] == box[-2]):
                        box.pop()
                        box.pop()
                        answer += 2
                break
    return answer

board =[[0,0,0,0,0],
        [0,0,1,0,3],
        [0,2,5,0,1],
        [4,2,4,4,2],
        [3,5,1,3,1]]

moves = [1,5,3,5,1,2,1,4]
answer = solution(board, moves)
print(answer)

