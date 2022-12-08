number_grid = []
visible_grid = []
max_scenic_score = 0

while True:
    try:
        grid_line = input()
        number_grid_line = []
        for i in grid_line:
            number_grid_line.append(int(i))
        number_grid.append(number_grid_line)
    except EOFError:
        break

n = len(number_grid)

visible_grid.append([True] * n)
for i in range(n - 2):
    visible_grid.append([True] + [False] * (n - 2) + [True])
visible_grid.append([True] * n)

visible_counter = 4 * n - 4

for i in range(1, n - 1):
    for j in range(1, n - 1):
        mul = 1

        # check visible from left
        scenic_sum = 0
        for k in range(j - 1, -1, -1):
            if number_grid[i][k] >= number_grid[i][j]:
                scenic_sum += 1
                break
            scenic_sum += 1
            if k == 0 and not visible_grid[i][j]:
                visible_grid[i][j] = True
                # visible_from_left[i][j] = True
                visible_counter += 1
        mul *= scenic_sum

        # check visible from right
        scenic_sum = 0
        for k in range(j + 1, n):
            if number_grid[i][k] >= number_grid[i][j]:
                scenic_sum += 1
                break
            scenic_sum += 1
            if k == n - 1 and not visible_grid[i][j]:
                visible_grid[i][j] = True
                # visible_from_right[i][j] = True
                visible_counter += 1
        mul *= scenic_sum

        # check visible from top
        scenic_sum = 0
        for k in range(i - 1, -1, -1):
            if number_grid[k][j] >= number_grid[i][j]:
                scenic_sum += 1
                break
            scenic_sum += 1
            if k == 0 and not visible_grid[i][j]:
                visible_grid[i][j] = True
                visible_counter += 1
        mul *= scenic_sum

        # check visible from bottom
        scenic_sum = 0
        for k in range(i + 1, n):
            if number_grid[k][j] >= number_grid[i][j]:
                scenic_sum += 1
                break
            scenic_sum += 1
            if k == n - 1 and not visible_grid[i][j]:
                visible_grid[i][j] = True
                visible_counter += 1
        mul *= scenic_sum

        if mul > max_scenic_score:
            max_scenic_score = mul

print("Number of visible trees:", visible_counter)
print("Maximum scenic score of a tree:", max_scenic_score)
