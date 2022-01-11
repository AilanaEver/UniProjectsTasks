function Student(firstName, lastName, id, grades) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.grades = grades;

    this.gradesAvg = function () {
        return grades.reduce((a,b)=>a+b)/grades.length;
    }

    this.printStudentInfo = function () {
        console.log("Student", firstName, lastName, "has average grade:", this.gradesAvg());
    }
}

let student = new Student("Susan", "Milton", 1, [5, 5, 3, 4, 4, 3]);
student.printStudentInfo();
