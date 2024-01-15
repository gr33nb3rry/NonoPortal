import React, { useState, useCallback } from 'react';
import './PixelArtDrawingPage.css'; // Импортируем файл стилей для стилизации, если это необходимо

const PixelArtDrawingPage = () => {
    const [gridSize] = useState(5);
    const [grid, setGrid] = useState(Array(gridSize * gridSize).fill('#FFFFFF'));
    const [gridArea, setGridArea] = useState(Array(gridSize * gridSize).fill(false));
    const [selectedColor, setSelectedColor] = useState('#000000');
    const [isDrawn, setIsDrawn] = useState(false);
    const [colors] = useState(['#000000', '#FF0000', '#00FF00',
        '#0000FF', '#FFFF00', '#FF00FF', '#FFA500', '#008080',
        '#FFD1DC', '#FFB6C1', '#FF69B4', '#FF6347', '#FF4500',
        '#FFD700', '#F0E68C', '#EEE8AA', '#98FB98', '#87CEEB',
        '#FFA07A', '#FF8C00', '#FF4500', '#FFD700', '#FF69B4',
        '#FF6347', '#87CEEB', '#00BFFF', '#32CD32', '#ADFF2F',
        '#FFDAB9', '#FFE4C4', '#FFDEAD', '#F0FFF0', '#E6E6FA',
        '#D8BFD8', '#DDA0DD', '#BA55D3', '#9370DB', '#8A2BE2']);

    const handleCellClick = useCallback((color, index) => {
        setGrid((prevGrid) => {
            const newGrid = [...prevGrid];
            newGrid[index] = color;
            return newGrid;
        });
    }, []);
    const handleCellClickArea = useCallback((index) => {
        setGridArea((prevGrid) => {
            const newGrid = [...prevGrid];
            newGrid[index] = !newGrid[index]
            return newGrid;
        });
    }, []);
    const handleColorChange = useCallback((color) => {
        setSelectedColor(color);
    }, []);
    const handleCompleteDrawing = useCallback((drawn) => {
        setIsDrawn(!drawn);
    }, []);
    const handleCheckDrawing = useCallback((gridArea, size) => {
        defineDifficulty(gridArea, size);
    }, []);

    const gridStyles = {
        display: 'grid',
        //opacity: 0.5,
        gridTemplateColumns: `repeat(${gridSize}, 40px)`,
        gap: '2px',
    };
    const gridStylesArea = {
        display: 'grid',
        opacity: 0.5,
        gridTemplateColumns: `repeat(${gridSize}, 40px)`,
        position: 'absolute',
        visibility: `${isDrawn === false ? 'hidden' : 'visible'}`,
        gap: '2px',
    };
    // Возвращаем JSX разметку компонента
    return (
        <div>
            <div className="pixel-art-container">
                <div className="pixel-art-grid" style={gridStyles}>
                    {grid.map((cell, index) => (
                        <div
                            key={index}
                            className={`grid-cell`}
                            style={{ backgroundColor: cell }}
                            onClick={() => handleCellClick(selectedColor, index)}
                        />
                    ))}
                </div>
                <div className="pixel-art-grid-area" style={gridStylesArea}>
                    {gridArea.map((cell, index) => (
                        <div
                            key={index}
                            className={`grid-cell ${cell ? 'filled' : ''}`}
                            style={{ backgroundColor: cell }}
                            onClick={() => handleCellClickArea(index)}
                        />
                    ))}
                </div>
            </div>
            <button
                style={{
                    backgroundColor: 'white',
                    width: '60px',
                    height: '40px',
                }}
                onClick={() => handleCompleteDrawing(isDrawn)}>
                {isDrawn === false ? 'Next' : 'Back'}
            </button>
            <button
                style={{
                    backgroundColor: 'white',
                    width: '60px',
                    height: '40px',
                }}
                onClick={() => handleCheckDrawing(gridArea, gridSize)}>
                Check
            </button>
            <div className="color-buttons">
                {colors.map((color, index) => (
                    <button
                        style={{
                            backgroundColor: color,
                            width: '40px',
                            height: '40px',
                        }}
                        onClick={() => handleColorChange(color)}>
                    </button>
                ))}
            </div>
            {selectedColor}
            <br />
            {grid}
            <div className="grid-display">
                {gridArea.map((cell, index) => (
                    <span key={index} className={`grid-cell-display ${cell ? 'filled' : ''}`}>
                        {cell ? '1' : '0'}
                        {(index + 1) % gridSize === 0 && <br />} {/* Перевод строки после каждых 5 клеток */}
                    </span>
                ))}
            </div>
        </div>
    );
};

export default PixelArtDrawingPage;

function defineDifficulty(grid, size) {
    const arr = arrayTo2DArray(grid);
    console.log(arr);
    const isCorrect = hasAtLeastOneTrueInRowsAndColumns(arr);
    console.log(isCorrect);

}
const arrayTo2DArray = (arr) => {
    const result = [];
    const size = Math.sqrt(arr.length);
    for (let i = 0; i < arr.length; i += size) {
        result.push(arr.slice(i, i + size));
    }
    return result;
}

const hasAtLeastOneTrueInRowsAndColumns = (grid2DArray) => {
    // Проверка строк
    for (let row of grid2DArray) {
        if (!row.includes(true)) {
            return false;
        }
    }

    // Проверка колонок
    for (let col = 0; col < grid2DArray[0].length; col++) {
        let hasTrueInColumn = false;
        for (let row = 0; row < grid2DArray.length; row++) {
            if (grid2DArray[row][col] === true) {
                hasTrueInColumn = true;
                break;
            }
        }
        if (!hasTrueInColumn) {
            return false;
        }
    }

    return true;
};