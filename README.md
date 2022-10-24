# PriorityQueue
Flight PriorityQueue


To start, I created a Priority Queue implemented by java, with a comparator, the comparator will come into play later. The main method outputs the Menu selections that are available and asks for a user input. There are 4 different options that the user can make: upgrade your flight, cancel your upgrade, view the queue, and view who gets upgraded seats from the queue when the flight is ready to board. There is an additional option to exit out of the program. Using a switch case, I implemented the priorityQueue algorithm for option 1 (upgrade). The response given is to ask for the users priority status, then name. A new passenger object is then created from the class passenger. Once that passenger is created, it outputs a confirmation code created by the RandomNumber math function, which is multiplied by 100,000 to ensure no 2 passengers have the same confirmation code. The confirmation code is then stored into a HashMap, for easy access when a passenger requests to cancel their initial upgrade. When the passenger enters their priority status, it is linked with their name, and then the status is converted into a number. With super being 4, platinum 3, gold 2, silver 1. When a second passenger is added, the numbers given to their statuses get compared by the comparator, and it orders the queue accordingly. In addition, when each passenger gets created, a count initialized to 0 gets created. This count serves as the time, and each passengers count gets incremented by 1 when a new passenger is added to serve as how long they’ve been waiting in the queue. For cancellation in case 2, the user enters the confirmation code they received when they upgraded, and the algorithm gets that confirmation code from the Hashmap and then removes it from itself and the queue. This only takes O(1) time. Case 3 just outputs the queue in order of status and time (if there is a tie). Case 4 will output the passengers who received upgraded seats for the flight. There are 10 seats available set by numseats, so if there are more than 10 in the queue, only the first 10 will receive seats, while the rest will still be in the queue, starting with the first person who missed the cut. 
