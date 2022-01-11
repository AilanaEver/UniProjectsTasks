let student = {
    firstName: 'Susan',
    lastName: 'Milton',
    id: 1,
    grades: [5, 5, 3, 4, 4, 3],
    get fullName() {
        return this.firstName + " " + this.lastName;
    },
    set fullName(name) {
        let word = name.split(' ');
        this.firsName = word[0];
        this.lastName = word[1];
    },
    get gradesAvg() {
         return this.grades.reduce((a,b)=>a+b)/this.grades.length;
    }
}

console.log(student.fullName, "has an average grade:", student.gradesAvg);
