function binary_Search(num, arr){
    var first  = 0,
        last  = arr.length - 1,
        mid = Math.floor((last + first)/2);

    while(arr[mid] != num && first < last){
       if (num < arr[mid]){
            last = mid - 1;
        }else if (num > arr[mid])
        {
            first = mid + 1;
        }
        mid = Math.floor((last + first)/2);
    }
  return (arr[mid] != num) ? -1 : mid;
}

console.log(binary_Search(3, [1, 2, 3, 4, 5, 6, 7]));
