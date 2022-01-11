function amountTocoins(amount, coins) 
{
 if (amount === 0){
     return [];
   }else if (amount >= coins[0]){
        let money = (amount - coins[0]);
        return [coins[0]].concat(amountTocoins(money, coins) );
        }else{
         coins.shift();
         return amountTocoins(amount, coins);
        }
} 

console.log(amountTocoins(46, [25, 10, 5, 2,1]));
