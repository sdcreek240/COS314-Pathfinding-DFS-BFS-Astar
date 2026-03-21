# Wall-E Pathfinding: DFS vs BFS vs A* Search

## Description
Implementation and comparison of three search algorithms navigating a 100x100 grid 
with a U-shaped obstacle (concave trap) and 15% random noise.

## Requirements
- Java 8+
- makefile for ease of compilation and execution - NOT REQUIRED
- No external libraries required

## Setup & Execution
1. Clone repository
2. Compile: `make`
3. Run: `make run`

## Input
Program will prompt for:
- Seed value (for random noise generation) - Will default to current time if not given seed

## Output
- The initial grid generated will be displayed showing the u shaped obstacle, the Start and Goal nodes in color and as well the accompanying 15% noise on the grid.
- The DFS -> BFS -> A* algorithms will execute and will dispaly their paths and final grids once completed.
- White full stops indicate available open nodes to travel to, blue full stops indicate a node that has been VISITED and a '|' indicates the path followed from Start to Goal.
- At the end of displaying all three algorithms path's the statistics will be displayed showcasing, in order, the Algorithm + Path length + Nodes Visited + Execution Time.
- Example statistics are provided below with the seed value of *0*

## Results Summary
| Algorithm | Path Cost | Nodes Visited | Time (ms) | Optimal? |
|-----------|-----------|---------------|-----------|----------|
| DFS       |   775     |       3534    | 19.0000ms |     no   |
| BFS       |    105    |       7396    | 23.0000ms |     yes  |
| A*        |     105   |       1712    | 29.0000ms |    yes   |