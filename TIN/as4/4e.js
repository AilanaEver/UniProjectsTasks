function longestWord(str) {
  var word = str.split(' ').sort((a, b) => b.length - a.length);
  return word[0];
}

console.log(longestWord("I do something that I did not do"));
