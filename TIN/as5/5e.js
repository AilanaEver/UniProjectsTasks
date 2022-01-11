class Student {
    constructor(firstName, lastName, id, grades) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.grades = grades;
    }

    get fullName() {
        return this.firstName + " " + this.lastName;
    }

    set fullName(name) {
        let word = name.split(' ');
        this.firsName = word[0];
        this.lastName = word[1];
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
