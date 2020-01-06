function mathAdd(a,b){  //加
    return math.add(math.bignumber(a),math.bignumber(b)).valueOf();
}

function mathSubtract(a,b){  //减
    return math.subtract(math.bignumber(a),math.bignumber(b)).valueOf();
}

function mathMultiply(a,b){  //乘
     try {
         if (!a) a = 0;
         if (!b) b = 0;
         return math.multiply(math.bignumber(a),math.bignumber(b)).valueOf();
     }catch (e) {
         return 0;
     }
}

function mathDivide(a,b){  //除
    return math.divide(math.bignumber(a),math.bignumber(b)).valueOf();
}
