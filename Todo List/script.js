let list = ['Buy chicken','Make Chicken Curry'];
let input = prompt("What would you like to do?")
while(input !== 'quit'){
    if (input == 'new'){
        let newinput = prompt("Add to the list");
        list.push(newinput);
        console.log(`${newinput} added todo list`)
    }else if(input === 'list'){
        console.log(' ')
        console.log('You ToDo list: ')
        for (let i = 0; i < list.length; i++) {
            console.log( `${i} : ${list[i]}`)
        }
    }else if(input == 'delete'){
        console.log(' ');
        console.log('Select one: ');
        for (let i = 0; i < list.length; i++) {
            console.log( `${i} : ${list[i]}`);
        }
        const delinput = parseInt(prompt('Enter the number you want to delete '));
        if (!Number.isNaN(delinput)) {
            const delitem = list.splice(delinput,1)
            console.log(`${delitem} - deleted`)
        } else {
            console.log('Wrong input type')
        }
    }
    input = prompt("What would you like to do?")
}
console.log('quit the app');