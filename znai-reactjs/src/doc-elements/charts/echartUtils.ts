/*
 * Copyright 2022 znai maintainers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * finds max and min values from data and creates invisible line data series
 * so that chart scale remains the same for presentation mode as new values are revealed over time
 * @param data data to analyze
 */
export function createInvisibleLineSeries(data: any[][]) {
  const isNumbersX = typeof data[0][0] === "number";

  let minY = Number.MAX_SAFE_INTEGER;
  let maxY = Number.MIN_SAFE_INTEGER;

  let minX = isNumbersX ? Number.MAX_SAFE_INTEGER : data[0][0];
  let maxX = isNumbersX ? Number.MIN_SAFE_INTEGER : data[data.length - 1][0];

  for (let rowIdx = 0; rowIdx < data.length; rowIdx++) {
    if (isNumbersX) {
      minX = Math.min(minX, data[rowIdx][0]);
      maxX = Math.max(maxX, data[rowIdx][0]);
    }

    for (let yIdx = 1; yIdx < data[0].length; yIdx++) {
      minY = Math.min(minY, data[rowIdx][yIdx]);
      maxY = Math.max(maxY, data[rowIdx][yIdx]);
    }
  }

  return {
    data: [
      {
        value: isNumbersX ? [minX, minY] : minY,
        itemStyle: { color: "none" },
      },
      {
        value: isNumbersX ? [maxX, maxY] : maxY,
        itemStyle: { color: "none" },
      },
    ],

    lineStyle: { color: "none" },
  };
}

/**
 * creates partial data based on column and break point value
 * all the values after breakpoint are ignored
 * @param fullData
 * @param colIdx
 * @param breakpointX
 */
export function partialDataExcludingDataAfterPoint(
  fullData: any[][],
  colIdx: number,
  breakpointX: number | string | undefined
) {
  const partialData: any[][] = [];

  const isNumbersX = typeof fullData[0][0] === "number";

  for (let rowIdx = 0; rowIdx < fullData.length; rowIdx++) {
    const row = fullData[rowIdx];
    const x = row[0];

    if (isNumbersX && breakpointX !== undefined && x > breakpointX) {
      break;
    }

    partialData.push(isNumbersX ? [x, row[colIdx]] : row[colIdx]);

    if (!isNumbersX && x === breakpointX) {
      break;
    }
  }

  return partialData;
}
