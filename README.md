# Data-Structures-and-Algorithims-Homeworks


    HW1 - Design and implement simple hotel management system

Design and implement simple hotel management system. The System has users that
recepcionist and guests. Guests and recepccionist has abilities that can book a room, cancel
booking. Recepcionist have also abilities that check in and check out. Program need to keep this all
data on csv file. CSV file must include record that which rooms reserver by whom. So that, program
will create 2 CSV files that first one is system user's login info and second one hotel, guest,
checkin/out and room status info.System user can choose 2 choice that DefaultHotel(20 rooms) /
CustomHotel(50Room) when login system. This custom otel will only create main class for tests.
When if users choose default hotel, they see login menu that Guest Login (1) and
Recepcionist Login(2), they also can a choose that back to Hotels's Menu(0).
When if a user choose Guest Menu, program will ask guest name for login system. Guest
can input onw name. Program will create a guestID, and will keep this informations. After enter
own name, they can see 3 choose that Booking(1), Cancel Booking(2) and Go Back(0).
When if Guest choose Booking Menu, system will find first emty room and will reserved
this room(System is checking from room number 1 until last). So if Guest has been booked
succesfuly or not, program will show that on interface as recervation info(GuestID, GuestName,
RoomNumber).
When if Guest choose Cancel Booking Menu, system will check if it's really reserved and
will cancel booking. İf it 's succesfully(or not) end, prrogram will show that. Guest must know own
gust ID if they can cancel booking.
When if users choose Recepcionist Menu, system will ask recepcionist login info. It's default
as name:“admin” pass:“admin”. If recepcionist has been login succesfuly, will see 6 choose on
recepcionist menu that Booking(1), Cancel Booking(2), CheckIn(3), CheckOut(4),
ShowBooking(5), GoBack(0).
For Booking and Cancel Booking Menu is smilar like Guest. İf recepcionist choose booking
progrom will ask guest name for make booking. At the same time,system will keep same
information like guest.
If recepcionist choose checkIn orCheckOut menu, System will ask GuestID and
RoomNumber. After system check this information, if there are a booking given info, system will
make check in.if there are no booking, system will immediately create a boking and will make
check in. If recepcionist choosed checkout, system will cancel reservation or delete guest record if
there are a reservation.

    HW3 - a) Design a courses structer, b)extend linkedlist class, c) implement custom linkedlist

Construct a binary tree representation of general tree. Left root will childreen node and right
root will siblings. Need to extend BinaryTree class for implement this tree. There are must have 2
methots for search metod. First one levelOrderSearch that travese tree as level by level and return
found found node, second one postOrderSearch that traverse tree as post order way and return found
node. Class also need to print to the tree as general tree representation(Override preOrderTraverse).


    HW4 - 

a) Construct a binary tree representation of general tree. Left root will childreen node and right
root will siblings. Need to extend BinaryTree class for implement this tree. There are must have
2 methots for search metod. First one levelOrderSearch that travese tree as level by 
level and return    found found node, second one postOrderSearch that traverse tree as 
post order way and return found node. Class also need to print to the tree as general 
tree representation(Override preOrderTraverse).

b) Construct a binary tree representation of general tree. Left root will childreen node and 
right root will siblings. Need to extend BinaryTree class for implement this tree. 
There are must have 2methots for search metod. First one levelOrderSearch that travese tree as
level by level and returnfound found node, second one postOrderSearch that traverse tree as 
post order way and return found node. Class also need to print to the tree as general tree
representation(Override preOrderTraverse).


    HW5 - Double Hashing Map, Recursive Hashing Set, Sorting Algorithms(MergeSort with 
    DoubleLinkedList, Merge Sort, Insertion Sort, Quick Sort, Heap Sort), Comparison the Analysis,


Implementation of Java Map by double hashing. First hash is calculating index for table. İf there areany collision then hash2 will callculate new index for table. After that, if there are any   collision then wil apply lineer probing.

Implementation of Java Set by recursive hashing. Hash1 is calculating index for table. 
İf there are any collision then call new table for hash it again and callculate new index for table,
so it is recursively. Next table 's capacity is calculeted as prime number that smaller then table 
size, if the new table's capacity is smaller then 1/3 of table size,then multiple the size as 4x+1. So, the tables size decreasing firstlty


    HW6 - 1 Worst RedBlack Tree, 2 - binarySearch Method, 3 - AVLTree(BinaryTree tree)


We need maximum rotation and recoloring while insert elements for worst Red&Black Tree. For
that, we need to insert minimum 14 element up to level 6 of tree. Tree can rotate maximum 2 times
for one insert (3 rotate max for deletion). If we want to cause 2 rotations, we need to add element to
parant.right.left.right (or the opposite). So, this is worst senario while add element. I created a
sequence for provide this. Tree will recoloring in a second step, and tree will double rotating in a
second step(first step recoloring, second step double rotating – generally up to level 6)
Worst case always O (log n) asymptoticly but in real, we can analysis rotate and re color count. This
execuitons are being constant time( tetha 1). We can think rotate and recolor count for worst case.

    
    HW7 - plot_graph ve dijkstrasAlgorithm : finding shortest path

