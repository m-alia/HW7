/******************************************************************
 *
 *   Malia Kuykendall / COMP 272/400C-001
 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

 import java.util.Arrays;
import java.util.Collections;

 public class ProblemSolutions {
 
     /**
      * Method SelectionSort
      *
      * This method performs a selection sort. This file will be spot checked,
      * so ENSURE you are performing a Selection Sort!
      *
      * This is an in-place sorting operation that has two function signatures. This
      * allows the second parameter to be optional, and if not provided, defaults to an
      * ascending sort. If the second parameter is provided and is false, a descending
      * sort is performed.
      *
      * @param values        - int[] array to be sorted.
      * @param ascending     - if true,method performs an ascending sort, else descending.
      *                        There are two method signatures allowing this parameter
      *                        to not be passed and defaulting to 'true (or ascending sort).
      */
 
     public  void selectionSort(int[] values) {
         selectionSort(values, true);
     }
 
     public static void selectionSort(int[] values, boolean ascending ) {
         int n = values.length;
         for (int i = 0; i < n - 1; i++) {
            int min = i;
            for (int j = i + 1; j < n; j++) {
                if (ascending == false) {
                    if (values[j] > values[min]) {
                        min = j;
                    }
                } else {
                    if (values[j] < values[min]) {
                        min = j;
                    }
                }
            }
            // nested loop
                // repeatedly goes through list, starting at index 0, then 1, etc. and finds min/max value
                    // (depending on whether ascending is true or false)
            if (min != i) {
                int tempVal = values[i];
                values[i] = values[min];
                values[min] = tempVal;
            }
            // then moves this value to the sorted portion of the array

            // if ascending, repeatedly select the SMALLEST unsorted element and move to sorted section
            // if descending, repeatedly select the LARGEST unsorted element and move to sorted section

             // YOU CODE GOES HERE -- COMPLETE THE INNER LOOP OF THIS
             // "SELECTION SORT" ALGORITHM.
             // DO NOT FORGET TO ADD YOUR NAME / SECTION ABOVE
         }
 
     } // End class selectionSort
 
 
     /**
      *  Method mergeSortDivisibleByKFirst
      *
      *  This method will perform a merge sort algorithm. However, all numbers
      *  that are divisible by the argument 'k', are returned first in the sorted
      *  list. Example:
      *        values = { 10, 3, 25, 8, 6 }, k = 5
      *        Sorted result should be --> { 10, 25, 3, 6, 8 }
      *
      *        values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
      *        Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
      *
      * As shown above, this is a normal merge sort operation, except for the numbers
      * divisible by 'k' are first in the sequence.
      *
      * @param values    - input array to sort per definition above
      * @param k         - value k, such that all numbers divisible by this value are first
      */
 
     public void mergeSortDivisibleByKFirst(int[] values, int k) {
 
         // Protect against bad input values
         if (k == 0)  return;
         if (values.length <= 1)  return;
 
         mergeSortDivisibleByKFirst(values, k, 0, values.length-1);
     }
 
     private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {
 
         if (left >= right)
             return;
 
         int mid = left + (right - left) / 2;
         mergeSortDivisibleByKFirst(values, k, left, mid);
         mergeSortDivisibleByKFirst(values, k, mid + 1, right);
         mergeDivisbleByKFirst(values, k, left, mid, right);
     }
 
     /*
      * The merging portion of the merge sort, divisible by k first
      */
 
     private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right)
     {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        // create left and right arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[i + left];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + j + 1];
        }
        // fill left and right arrays

        int z = 0;
        int[] tempArr = new int[right-left+1];
   
        for (int i = 0; i < n1; i++) {
            if (leftArr[i] % k == 0) {
                tempArr[z] = leftArr[i];
                z++;
            }
        }
        for (int j = 0; j < n2; j++) {
            if (rightArr[j] % k == 0) {
                tempArr[z] = rightArr[j];
                z++;
            }
        }
        // pull out values divisible by k first, since they won't be put into the sorting algorithm
        // add all divisible values to front of temp array, and increment z to move starting index depending
        // on number of values that are divisible by k

        int x = 0;
        int y = 0;
        // x and y store current index in left and right arrays respectively
        while (x < n1 && y < n2) {
            while (x < n1 && leftArr[x] % k == 0) {
                x++;
            }
            while (y < n2 && rightArr[y] % k == 0) {
                y++;
            }
            // while iterating through arrays, if value is divisible by k, it's already in the temp array
            // so skip this value, and move to the next one
            // repeat until value that isn't divisible by k is found
            
            if (x < n1 && (y >= n2 || leftArr[x] <= rightArr[y])) {
                tempArr[z] = leftArr[x];
                x++;
                // if x there are more elements remaining in the left array, AND either the right array is out of values
                    // or the left value is less than the right value, we want to add the left element into the array
                    // then, increment x by one
            } else if (y < n2) {
                tempArr[z] = rightArr[y];
                y++;
            } // otherwise, if we aren't adding from the left, then we will add from the right
                // as long as there are elements left to be added from the right array
            z++;
        }

        while (x < n1) {
            if (leftArr[x] % k != 0) {
                tempArr[z] = leftArr[x];
                z++;
            }
            x++;
        } // if there's any elements remaining in the left array, and they aren't divisible by k, add them to the temp array

        while (y < n2) {
            if (rightArr[y] % k != 0) {
                tempArr[z] = rightArr[y];
                z++;
            }
            y++;
        } // if there's any elements remaining in the right array, and they aren't divisible by k, add them to the temp array

        for (int i = 0; i < tempArr.length; i++) {
            arr[left + i] = tempArr[i];
        } // once temp array is fully sorted, copy temp array over to the original array


         // YOUR CODE GOES HERE, THIS METHOD IS NO MORE THAN THE STANDARD MERGE PORTION
         // OF A MERGESORT, EXCEPT THE NUMBERS DIVISIBLE BY K MUST GO FIRST WITHIN THE
         // SEQUENCE PER THE DISCUSSION IN THE PROLOGUE ABOVE.
         //
         // NOTE: YOU CAN PROGRAM THIS WITH A SPACE COMPLEXITY OF O(1) OR O(N LOG N).
         // AGAIN, THIS IS REFERRING TO SPACE COMPLEXITY. O(1) IS IN-PLACE, O(N LOG N)
         // ALLOCATES AUXILIARY DATA STRUCTURES (TEMPORARY ARRAYS). IT WILL BE EASIER
         // TO CODE WITH A SPACE COMPLEXITY OF O(N LOG N), WHICH IS FINE FOR PURPOSES
         // OF THIS PROGRAMMING EXERCISES.
 
     }
 
 
     /**
      * Method asteroidsDestroyed
      *
      * You are given an integer 'mass', which represents the original mass of a planet.
      * You are further given an integer array 'asteroids', where asteroids[i] is the mass
      * of the ith asteroid.
      *
      * You can arrange for the planet to collide with the asteroids in any arbitrary order.
      * If the mass of the planet is greater than or equal to the mass of the asteroid, the
      * asteroid is destroyed and the planet gains the mass of the asteroid. Otherwise, the
      * planet is destroyed.
      *
      * Return true if possible for all asteroids to be destroyed. Otherwise, return false.
      *
      * Example 1:
      *   Input: mass = 10, asteroids = [3,9,19,5,21]
      *   Output: true
      *
      * Explanation: One way to order the asteroids is [9,19,5,3,21]:
      * - The planet collides with the asteroid with a mass of 9. New planet mass: 10 + 9 = 19
      * - The planet collides with the asteroid with a mass of 19. New planet mass: 19 + 19 = 38
      * - The planet collides with the asteroid with a mass of 5. New planet mass: 38 + 5 = 43
      * - The planet collides with the asteroid with a mass of 3. New planet mass: 43 + 3 = 46
      * - The planet collides with the asteroid with a mass of 21. New planet mass: 46 + 21 = 67
      * All asteroids are destroyed.
      *
      * Example 2:
      *   Input: mass = 5, asteroids = [4,9,23,4]
      *   Output: false
      *
      * Explanation:
      * The planet cannot ever gain enough mass to destroy the asteroid with a mass of 23.
      * After the planet destroys the other asteroids, it will have a mass of 5 + 4 + 9 + 4 = 22.
      * This is less than 23, so a collision would not destroy the last asteroid.
      *
      * Constraints:
      *     1 <= mass <= 105
      *     1 <= asteroids.length <= 105
      *     1 <= asteroids[i] <= 105
      *
      * @param mass          - integer value representing the mass of the planet
      * @param asteroids     - integer array of the mass of asteroids
      * @return              - return true if all asteroids destroyed, else false.
      */
 
     public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        boolean possibleToDestroy = true;
        // assume that it's possible for all asteroids to be destroyed
        Arrays.sort(asteroids);
        // sort asteroids from smallest to largest
        int totalMass = mass;
        for (int i = 0; i < asteroids.length; i++) {
            if (totalMass < asteroids[i]) {
                possibleToDestroy = false;
            }
            totalMass += asteroids[i];
        } // since the asteroid array has been sorted, we can check from index 0 and up whether the planet's mass is large enough
        // if the mass of the planet is less than the next smallest asteroid in the array, then it's not possible to destroy all the
        // asteroids, since we're continually checking against the smallest asteroid that hasn't been destroyed
        
         // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT()
 
         return possibleToDestroy;
     }
 
 
     /**
      * Method numRescueSleds
      *
      * You are given an array people where people[i] is the weight of the ith person,
      * and an infinite number of rescue sleds where each sled can carry a maximum weight
      * of limit. Each sled carries at most two people at the same time, provided the
      * sum of the weight of those people is at most limit. Return the minimum number
      * of rescue sleds to carry every given person.
      *
      * Example 1:
      *    Input: people = [1,2], limit = 3
      *    Output: 1
      *    Explanation: 1 sled (1, 2)
      *
      * Example 2:
      *    Input: people = [3,2,2,1], limit = 3
      *    Output: 3
      *    Explanation: 3 sleds (1, 2), (2) and (3)
      *
      * Example 3:
      *    Input: people = [3,5,3,4], limit = 5
      *    Output: 4
      *    Explanation: 4 sleds (3), (3), (4), (5)
      *
      * @param people    - an array of weights for people that need to go in a sled
      * @param limit     - the weight limit per sled
      * @return          - the minimum number of rescue sleds required to hold all people
      */
 
     public static int numRescueSleds(int[] people, int limit) {
        int totalSleds = 0;
        int peopleOnSled = 0;
        int currentSledWeight = 0;
        Arrays.sort(people);
        // sorting the array from smallest to largest
        // so we have better chances of 2 consecutive people being able
        // to fit under the weight limit

        for (int i = 0; i < people.length; i++) {
            if (currentSledWeight + people[i] <= limit) {
                peopleOnSled++;
                currentSledWeight += people[i];
            }
            if (peopleOnSled >= 2 || currentSledWeight + people[i] > limit) {
                totalSleds++;
                peopleOnSled = 0;
                currentSledWeight = 0;
            }
        }
        // iterate through entire people array
            // check if the current weight of sled can hold the next person,
            // if so, add them to sled, and increase counter for people on the sled
            
            // if amount of people on the sled is at 2, or there isn't room for another person,
                // we have a full sled, so increase sled count
                // we also need a new sled, so reset current weight and people on the sled

         // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT
         return totalSleds;
     }
 
 } // End Class ProblemSolutions
 