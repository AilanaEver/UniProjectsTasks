class Person {
    constructor(firstName, lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    get fullName() {
        return this.firstName + " " + this.lastName;
    }

    set fullName(name) {
        let word = name.split(' ');
        this.firsName = word[0];
        this.lastName = word[1];
    }
}

class Student extends Person {
    constructor(firstName, lastName, id, grades) {
        super(firstName, lastName);
        this.id = id;
        this.grades = grades;
    }

    get gradesAvg() {
        return this.grades.reduce((a,b)=>a+b)/this.grades.length;
    }

    printStudentInfo() {
        console.log(student.fullName, "has an average grade:", student.gradesAvg);
    }
}

let student = new Student("Susan", "Milton", 1, [5, 5, 3, 4, 4, 3]);
student.printStudentInfo();
