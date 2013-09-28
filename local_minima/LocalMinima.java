public class LocalMinima {
    // this makes use of modified binary search algorithm
    // if value at mid is less than its immediate neighbors, it is the local minimum
    // 1. move in the left direction if the value at mid is greater than its left neighbor
    // 2. move in the right direction if the value at mid is greater than its right neighbor
    // if 1. or 2. conditions are not met with, picking left or right would not matter, I picked left
    public static int localMinima(int[] inputArray) {
        if(inputArray == null) {
            return Integer.MIN_VALUE;
        }
        if(inputArray.length == 0) {
            return Integer.MIN_VALUE;
        }

        // special case: when length of the array is 2
        if(inputArray.length == 2) {
            if(inputArray[0] <= inputArray[1]) {
                return inputArray[0];
            } else {
                return inputArray[1];
            }
        }

        int low = 0;
        int high = inputArray.length - 1;
        while(low <= high) {
            int mid = (high + low)/2;
            
            // corner case: when mid is the first element of the array
            if(mid == 0) {
                if(low == mid && high == mid) {
                    return inputArray[mid];
                }
                if(inputArray[mid] < inputArray[mid+1]) {
                    return inputArray[mid];
                } else {
                    return Integer.MIN_VALUE;
                }
            }

            // corner case: when mid is the last element of the array
            if(mid == inputArray.length - 1) {
                if(low == mid && high == mid) {
                    return inputArray[mid];
                }
                if(inputArray[mid] < inputArray[mid-1]) {
                    return inputArray[mid];
                } else {
                    return Integer.MIN_VALUE;
                }
            }

            if(inputArray[mid] <= inputArray[mid-1] && inputArray[mid] <= inputArray[mid+1]) {
                return inputArray[mid];
            }
            if(inputArray[mid] > inputArray[mid-1]) {
                high = mid - 1;
            } else if(inputArray[mid] > inputArray[mid+1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return Integer.MIN_VALUE;
    }

    public static void main(String[] args) {
        //int[] inputArray = {45, 12};
        int[] inputArray = {16, 16, 16, 34, 32, 45, 12};
        //int[] inputArray = {1, 3, 4, 5, 12, 10, 14, 9};
        //int[] inputArray = {1};
        //int[] inputArray = {1, 2};
        //int[] inputArray = {2, 1};
        //int[] inputArray = {10, 5, 1};
        //int[] inputArray = {1, 3, 4};
        //int[] inputArray = {5, 3, 4};
        System.out.println("local minima [" + localMinima(inputArray) + "]");
    }
}
