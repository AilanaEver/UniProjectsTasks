function palindrome(str) {
    for (let i = 0, j = str.length - 1; i < j; i++, j--) {
        if (str[i] !== str[j]) return false;
    }
    return true;
}

console.log(palindrome('mom'));
console.log(palindrome('mommy'));
