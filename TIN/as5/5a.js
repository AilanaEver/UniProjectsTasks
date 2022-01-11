let parameter = {
    name: "Susan",
    height: 165,
    weight: 58,
    normalWeight: function () {
        return this.height - 100;
    },
    nameLength: function () {
        return this.name.length;
    }
}

function printProperties(obj){
    Object.entries(obj).forEach(word=>{
        console.log("property: " + word[0] + "\ntype: " + typeof(word[1]))
    })   
}

printProperties(parameter);
