const factorialRecursion = num => {
    if (num <= 1) return 1;
    return num * factorialRecursion(num - 1);
}

function factorialIteration(num) {
    let factorial = 1;
    for (let i = 2; i <= num; i++)
        factorial *= i;
    return factorial
}

console.log(factorialRecursion(7));
console.log(factorialIteration(7));
