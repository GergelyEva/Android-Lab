<h3 align="center">Mobile Development (Android) Lab Exercises</h3>
<p align="center">Short summary of the contents of this repository.These are all exercises for Mobile Development Lab classes.</p>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Continut</summary>
  <ol>
    <li><a href="#lab-1">Lab 1</a></li>
    <li><a href="#lab-3">Lab 3</a></li>
    <li><a href="#lab-4">Lab 4</a></li>
    <li><a href="#lab-5-6">Lab 5-6</a></li>
    <li><a href="#lab-7-8">Lab 7-8</a></li>
    <li><a href="#lab-9-10">Lab 9-10</a></li>
  </ol>
</details>





### Lab 1

<p>This is the first lab of Android Studio, that is meant to evaluate Java skills. </p>
<pre>
1) Write a Java program to get a number from the user and print whether it is positive or negative
2) Write a Java program to calculate the average value of array elements.
3) Write a Java method to find all prime numbers less than 100.
4) Write a program that implement the concept of Encapsulation.
5) a) Create a super class called Car.
The Car class has the following fields and methods:
•int: speed;
•double: regularPrice;
•String: color;
•doubleget: SalePrice():
b) Create a sub class of Car class and name it as Truck.
The Truck class has the following fields and methods.
•intweight;
•double: getSalePrice(); Extra information:if weight>2000,10% discount; otherwise,20% discount.
c) Create a subclass of Car class and name it as Ford.
The Ford class has the following fields and methods:
•int: year;
•int: manufacturerDiscount;
• double: getSalePrice(); 
Extra information: From the saleprice from Car class, subtract the manufacturerDiscount.
d) Create a subclass of Car class and name it as Sedan.
The Sedan class has the following fields and methods:
•int: length;
•doubleget: SalePrice();
Extra information: if length>20feet, 5%discount; otherwise, 10%discount
e) Create MyOwnAutoShop class which contains the main method. 
Perform the following within the main method:
•Create an instance of Sedan class and initialize all the fields with appropriate values.
•Create two instances of the Ford class and initialize all the fields with appropriate values. 
•Create an instance of Car class and initialize all the fields with appropriate values.
Display the sale prices of all instance.
</pre>

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Lab 3

<h4 align="left">Couunter app</h4>
File name: androidlab3
<pre>
• The "Counter" app will consist of two buttons and one text view. When the first button is clicked, it will display a short message, or toast, on the screen.
• Clicking on the second button will increase a click counter; the total count of mouse clicks will be displayed in the text view.
</pre>
<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Lab 4

<h5 align="left">SimpleCalc app</h5>
File name: AndroidLab4Calculator

The SimpleCalc app has two edit texts and four buttons. When you enter two numbers and click a button, the app performs the calculation for that button and displays the result.


<h5 align="left">Book app</h5>
File name: AndroidLab4Fragment
Simulate a book app with at least 4 chapters. Each chapter has the possibility of direct access.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Lab 5-6

<h5 align="left">Intents app</h5>
File name: AndroidLaborator56Intentions

Test at least 3 different intentions

<h5 align="left">Phone book app</h5>
File name: AndroidLaborator56PhoneBookApp

Add new contacts, edit their details.

<h5 align="left">Chat app</h5>
File name: ChatAppFirebaseAuthenticationRelatimeDatabase <br>
!This one was built using Firebase, instead of intents. <br>

Create a small chat between 2 users using intents. Use text-file, buttons etc. Use at least 3 different design elements.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Lab 7-8


<h5 align="left">Broadcast Receiver app</h5>
File name: AndroidLab78Battery2 + AndroidLab78Battery2BroadcastReceiverApp

Send broadcasts when the bacttery changed and when the battery is low.

<h5 align="left">Stop watch app</h5>
File name: AndroidLab78StopWatch

<h5 align="left">Tic Tac Toe game (X & 0)</h5>
File name: AndroidLab78TicTacToe

In the backend, the game will maintain a 2-D array to save the current state of the 3x3 grid. A function will be made to check if the box that's clicked by the player in the displaying grid is empty or not and will decide to put up an image of O if the previous one was X and vice versa. And when the consecutive images match, the game
ends.

<h5 align="left">Contest app</h5>
File name: AndroidLab78Ex4Competition
<pre>
At the annual programming competition, the evaluation committee records in a list the score obtained by each participant.
The following information is recorded about each participant: Name, Surname, Score. Each participant must solve 10 problems, the
maximum points for each problem is 10.
Create an application that allows the organizing committee to:
a) Add participants
b) Update existing participant
C) Delete participants
d) Allows the display of participants filtered by a property (those who have a score lower than a given value, those with names starting with a given letter, etc.)
e) Allows sorting of participants by: name or by score (ascending/descending)
</pre>
<h5 align="left">Voting app</h5>
File name: otpapp3 (with Firebase OTP), otpapp4

Standing in long queues and waiting for your turn for voting is no less than a challenge. This is where the online voting system comes into play. It will click the snap of the voters and verify it with the already existing data. And once the identity is confirmed, OTP (One Time Password) will be sent to their phone numbers. Subsequently, the voters can vote in a hassle-free manner.
This will have two modules- admin and user. The admin will create and schedule voting and users will vote. There will be OTP generation and voting.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


### Lab 9-10

<h5 align="left">Http request using okHttp with Json</h5>
File name: AndroidLab910OkHTTPJSON

<h5 align="left">Http request using vollay with Json</h5>
File name: AndroidLab910VolleyJSON

<h5 align="left">Send & Receive data from Android to PC (TCP Sockets)</h5>
File name: AndroidLab910TCPSockets2

<p align="right">(<a href="#readme-top">back to top</a>)</p>
