# Wall-E Pathfinding: DFS vs BFS vs A* Search

## Description
Implementation and comparison of three search algorithms navigating a 100x100 grid 
with a U-shaped obstacle (concave trap) and 15% random noise.

## Requirements
- Java 8+
- No external libraries required

## Setup & Execution
1. Clone repository
2. Compile: `javac src/main/*.java src/utils/*.java`
3. Run: `java -cp src main.Pathfinder`

## Input
Program will prompt for:
- Seed value (for random noise generation)
- File path (optional for output)
- Algorithm selection (DFS/BFS/A*)

## Output
- Path cost (distance units)
- Nodes visited count
- Execution time (ms)
- Optimality guarantee status

## Results Summary
| Algorithm | Path Cost | Nodes Visited | Time (ms) | Optimal? |
|-----------|-----------|---------------|-----------|----------|
| DFS       |           |               |           |          |
| BFS       |           |               |           |          |
| A*        |           |               |           |          |