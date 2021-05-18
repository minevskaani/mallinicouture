const {entropy, generateRandomArray, generateTable} = require("./random");

const readline = require("readline");

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

const inRange = (num, min, max) => {
  return num % (max - min + 1) + min;
};

const generateTables = number => {
  const generatedTable = generateTable(number)
  const table = Array.from({length: number}, (v, k) => {
    return {
      a: inRange(generatedTable[k], 0, 9),
      b: inRange(generatedTable[k], 10, 99),
      c: inRange(generatedTable[k], 100, 999)
    };
  });

  const algoRandoma = generateRandomArray(number).map(el => inRange(el, 0, 9));
  const algoRandomb = generateRandomArray(number).map(el => inRange(el, 10, 99));
  const algoRandomc = generateRandomArray(number).map(el => inRange(el, 100, 999));

  const algoTable = algoRandoma.map((el, i) => {
    return {
      a: el,
      b: algoRandomb[i],
      c: algoRandomc[i]
    };
  });

  return [table, algoTable];
};

const calcEstimations = table => {
  return [
    {
      method: 'Ентропия',
      a: entropy(table.map((el => el.a))),
      b: entropy(table.map((el => el.b))),
      c: entropy(table.map((el => el.c))),
    }
  ];
};

const formatForTable = table => {
  return table.map(el => {
    return {
      '0-9': el.a,
      '10-99': el.b,
      '100-999': el.c
    };
  });
};

const formatForTableEstimations = table => {
  return table.map(el => {
    return {
      method: el.method,
      '0-9': el.a,
      '10-99': el.b,
      '100-999': el.c
    };
  });
};

rl.question("Количество чисел: " , number => {
  number = +number;
  const [table, algoTable] = generateTables(number);

  const tableEstimations = calcEstimations(table);
  const algoTableEstimations = calcEstimations(algoTable);
  console.log('------------------------- Оценки -------------------------');
  console.log('Табличные: ');
  console.table(formatForTable(table));
  console.log('Алгоритмичные: ');
  console.table(formatForTable(algoTable));

  console.log('------------------------- Оценки -------------------------');
  console.log('Табличные: ');
  console.table(formatForTableEstimations(tableEstimations));

  console.log('Алгоритмичные: ');
  console.table(formatForTableEstimations(algoTableEstimations));

  rl.close();
});
