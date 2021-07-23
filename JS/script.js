console.log("Hello World!!")
let phrase = 'stop';
if (phrase == 'stop'){
    console.log('red')
} else if(phrase == 'slow'){
    console.log('yellow')
} else if (phrase == 'go'){
    console.log('green')
} else {
    console.log('purple')
}
const planets = ['The Moon','Venus', 'Earth', 'Mars', 'Jupiter'];
const product = {
    name:'Gummy Bears',
    inStock: true,
    price: 1.99,
    flavors:['grapes', 'apple', 'cherry']
};
const restaurant = {
    name: 'Ichiran Ramen',
    address: `${Math.floor(Math.random() * 100) + 1} Johnson Ave`,
    city: 'Brooklyn',
    state: 'NY',
    zipcode: '11206',
}
let fullAddress = `${restaurant.address}, ${restaurant.city}, ${restaurant.state} ${restaurant.zipcode}`

for (let index = 1; index <=6; index++) {
    console.log('Da ba dee da ba daa')
}
const people = ["Scooby", "Velma", "Daphne", "Shaggy", "Fred"];
for (let i = 0; i < people.length; i ++){
    console.log(i,people[i].toUpperCase())
}

const numbers = [1,2,3,4,5,6,7,8,9]; //DON'T CHANGE THIS LINE PLEASE!
console.log("   ")
// WRITE YOUR LOOP BELOW THIS LINE:
for (let count of numbers){
    console.log(Math.pow(count,2))
}

function printHeart(){
    console.log('<3');
}

function rant(message) {
    console.log(`${message.toUpperCase()}`);
    console.log(`${message.toUpperCase()}`);
    console.log(message.toUpperCase());
}

function isSnakeEyes(die1,die2) {
    if (die1 === 1 && die2 === 1){
        console.log('Snake Eyes!');
    } else {
        console.log('Not Snake Eyes!');
    }
}
 function multiply(x,y) {
     return x*y;
 }

 function isShortsWeather(temp) {
     if  (temp >= 75){
         return true;
     }
     return false;
 }

 function lastElement(ary) {
     if (ary.length===0){
        return null;
     }
     return (ary[ary.length-1]);
}

function capitalize(srt){
    let fSrt = srt.slice(0,1);
    let restSrt = srt.slice(1);
    return (fSrt.toUpperCase() + restSrt); 
}

function sumArray(ary) {
    let sum = 0;
    for (const element of ary) {
        sum += parseInt(element);
    }
    return sum;
}

const days = {
    1:'Monday',
    2:'Tuesday',
    3:'Wednesday',
    4:'Thursday',
    5:'Friday',
    6:'Saturday',
    7:'Sunday'
}

function returnDay(dayNo) {
    if (dayNo > 0 && dayNo < 8){
        return days[dayNo];  
    }
    return null;
}

const squareFunction = function (num) {
    return Math.pow(num,2);
}

const square = {
area: function(side){ return side**2;},
perimeter(side){return side*4;}
}

const hen ={
    name: 'Helen',
    eggCount: 0,
    layAnEgg(){
        this.eggCount++;
        return 'EGG';
    }
}

const fullNames = [{first: 'Albus', last: 'Dumbledore'}, {first: 'Harry', last: 'Potter'}, {first: 'Hermione', last: 'Granger'}, {first: 'Ron', last: 'Weasley'}, {first: 'Rubeus', last: 'Hagrid'}, {first: 'Minerva', last: 'McGonagall'}, {first: 'Severus', last: 'Snape'}];
const firstName = fullNames.map(function (name) {
    return name.first;
})

const greet = name => { return `Hi ${name}!`;}

setTimeout(function() {
    console.log('In timeout of 2sec')
},2000)

setTimeout(() => console.log('In timeout of 4sec'),4000)

const si = setInterval(() => {
    console.log('In every interval of 4 sec')
}, 4000);
clearInterval(si)

const uname = ['utkiad','fasodbfoasdf','oifsoiasdoif','fasdfasdfasdfasdfasdf','adf']
const validname = uname.filter(name => name.length<10)
const notvalidname = uname.filter(function (name) {
    return name.length >10;
})


const validUserNames = (username => username.filter(name => name.length<10))

//every
const allEvens = (numbers => numbers.every(num=> num%2 === 0))
//some
const someEvens = (numbers => numbers.some( num => num%2 == 0))

const user1 ={
    firstName: 'Utkrist',
    lastName: 'Shrestha'
}
const user2 ={
    firstName: 'Koila',
    lastName: 'Kumar'
}

const fullName = ({firstName,lastName}) => `${firstName} ${lastName}!!`
