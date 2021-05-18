import './App.css';
import {jStat} from 'jstat';
import {useEffect, useState} from "react";

import {CanvasJSChart} from 'canvasjs-react-charts';

const linspace = (startValue, stopValue, cardinality) => {
  const arr = [];
  const step = (stopValue - startValue) / (cardinality - 1);

  for (var i = 0; i < cardinality; i++) {
    arr.push(startValue + (step * i));
  }

  return arr;
}


function App() {
  const [xMin, setXMin] = useState(0);
  const [xMax, setXMax] = useState(20);

  // uniform
  const [a, setA] = useState(3);
  const [b, setB] = useState(17);
  const [uniformCdf, setUniformCdf] = useState([]);
  const [uniformPdf, setUniformPdf] = useState([]);

  // normal
  const [mean, setMean] = useState(7);
  const [std, setStd] = useState(1.21); // standard deviation
  const [normalCdf, setNormalCdf] = useState([]);
  const [normalPdf, setNormalPdf] = useState([]);

  const [optionsCdf, setOptionsCdf] = useState({});
  const [optionsPdf, setOptionsPdf] = useState({});

  // uniform
  // Математическое ожидание
  const [uniformMean, setUniformMean] = useState(0);
  // дисперсия
  const [uniformVariance, setUniformVariance] = useState(0);

  // normal
  // Математическое ожидание
  const [normalMean, setNormalMean] = useState(0);
  // дисперсия
  const [normalVariance, setNormalVariance] = useState(0);
  useEffect(() => {
    setUniformMean(jStat.uniform.mean(+a, +b));
    setUniformVariance(jStat.uniform.variance(+a, +b));
  }, [a, b]);
  useEffect(() => {
    setNormalMean(jStat.normal.mean(+mean, +std));
    setNormalVariance(jStat.normal.variance(+mean, +std));
  }, [mean, std]);

  useEffect(() => {
    const nums = linspace(+xMin, +xMax, 1000);

    // Равномерное
    // cdf - распределение
    // pdf - плотность распределения
    // Returns the value of x in the cdf of the Uniform distribution from a to b.
    setUniformCdf(nums.map(el => {
      return {
        x: el,
        y: jStat.uniform.cdf(el, +a, +b)
      };
    }));
    setUniformPdf(nums.map(el => {
      return {
        x: el,
        y: jStat.uniform.pdf(el, +a, +b)
      };
    }));

    setNormalCdf(nums.map(el => {
      return {
        x: el,
        y: jStat.normal.cdf(el, +mean, +std)
      };
    }));

    setNormalPdf(nums.map(el => {
      return {
        x: el,
        y: jStat.normal.pdf(el, +mean, +std)
      };
    }));

  }, [a, b, mean, std, xMin, xMax]);

  const setOptions = () => {
    setOptionsCdf({
      theme: "light2",
      animationEnabled: true,
      title:{
        text: "Функции распределения"
      },
      data: [{
        type: "line",
        name: "Равномерное",
        showInLegend: true,
        dataPoints: uniformCdf || []
      },
        {
          type: "line",
          name: "Нормальное",
          showInLegend: true,
          dataPoints: normalCdf || []
        }

      ]
    });
    setOptionsPdf({
      theme: "light2",
      animationEnabled: true,
      title:{
        text: "Функции плотности распределения"
      },
      data: [{
        type: "line",
        name: "Равномерное",
        showInLegend: true,
        dataPoints: uniformPdf || []
      },
        {
          type: "line",
          name: "Нормальное",
          showInLegend: true,
          dataPoints: normalPdf || []
        }
      ]
    });
  }

  useState(() => {
    setOptions();
  }, []);

  return (
    <div style={{display: 'flex', flexDirection: 'column', padding: "50px 50px"}}>
      <div style={{display: 'flex'}}>
        <label htmlFor='xMinInput'>Начало интервала: </label>
        <input id='xMinInput' type='input' value={xMin} onChange={e => setXMin(e.target.value)}/>

        <label htmlFor='xMaxInput'>Конец интервала: </label>
        <input id='xMaxInput' type='input' value={xMax} onChange={e => setXMax(e.target.value)}/>
      </div>
      <div>
        <div>Параметры для равномерного распределения:</div>
        <label htmlFor='aInput'>a: </label>
        <input id='aInput' type='input' value={a} onChange={e => setA(e.target.value)}/>

        <label htmlFor='bInput'>b: </label>
        <input id='bInput' type='input' value={b} onChange={e => setB(e.target.value)}/>
      </div>
      <div>
        <div>Параметры для Нормального распределения:</div>
        <label htmlFor='meanInput'>mean: </label>
        <input id='meanInput' type='input' value={mean} onChange={e => setMean(e.target.value)}/>

        <label htmlFor='stdInput'>std: </label>
        <input id='bInput' type='input' value={std} onChange={e => setStd(e.target.value)}/>
      </div>

      <div>
        <button onClick={setOptions}>Отобразить</button>
      </div>

      <div>Равномерное распределение:</div>
      <div>Математическое ожидание: <strong>{uniformMean}</strong>; Дисперсия: <strong>{uniformVariance}</strong>; Стандартное отклонение: <strong>{Math.sqrt(uniformVariance)}</strong></div>

      <div>Нормальное распределение:</div>
      <div>Математическое ожидание: <strong>{normalMean}</strong>; Дисперсия: <strong>{normalVariance}</strong>; Стандартное отклонение: <strong>{Math.sqrt(normalVariance)}</strong></div>

      <div style={{margin: '20px 0', display: 'flex'}}>
        <CanvasJSChart options={optionsCdf}/>
        <CanvasJSChart options={optionsPdf}/>
      </div>
    </div>
  );
}

export default App;
