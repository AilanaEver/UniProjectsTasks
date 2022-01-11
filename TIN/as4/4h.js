function secondLowestGreatest(arr) {
    arr = arr.sort((a, b) => a - b);
    return [arr[1], arr[arr.length - 2]];
}

console.log(secondLowestGreatest([3, 7, 1, 5, 9]));
