const studentPrototype = {
    firstName: "Susan",
    lastName: "Milton",
    id: 1,
    courses: [" Informatics", " Math", " English", " Management", " Physics"]
}

function objectBasedOnPrototype(firstName, lastName, id) {
    let student = Object.create(studentPrototype);
    student.firstName = firstName;
    student.lastName = lastName;
    student.id = id;
    return student
}

let student = objectBasedOnPrototype("Fill", "Murray", 2);
console.log(student.firstName + " " + student.lastName + " with id num " + student.id + " should take courses:"+ student.courses);
