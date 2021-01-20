While the most voted solutions are smart and brilliant, I can't wrap my head around to write their solutions in an actual interview. So here goes my solution that is  easy to understand:

1.Have a sub function nextDay() that finds the next day's cell states  
2.Iterate and store the cell states that occurred previously  
3.If there's no cycle, return. If there's a cycle, break the loop and rerun N%cycle times to find the target cell states

```
class Solution {
    public int[] prisonAfterNDays(int[] cells, int N) {
		if(cells==null || cells.length==0 || N<=0) return cells;
        boolean hasCycle = false;
        int cycle = 0;
        HashSet<String> set = new HashSet<>(); 
        for(int i=0;i<N;i++){
            int[] next = nextDay(cells);
            String key = Arrays.toString(next);
            if(!set.contains(key)){ //store cell state
                set.add(key);
                cycle++;
            }
            else{ //hit a cycle
                hasCycle = true;
                break;
            }
            cells = next;
        }
        if(hasCycle){
            N%=cycle;
            for(int i=0;i<N;i++){
                cells = nextDay(cells);
            }   
        }
        return cells;
    }

    private int[] nextDay(int[] cells){
        int[] tmp = new int[cells.length];
        for(int i=1;i<cells.length-1;i++){
            tmp[i]=cells[i-1]==cells[i+1]?1:0;
        }
        return tmp;
    }
}
```

Complexity Analysis:  
As the cells have a fixed size of 8 but the first and last cell will not be updated because they do not have two adjacent neighbors, we have at most 2^6 = 64 states. So regardless of input N, we have both space and time complexities as O(1).