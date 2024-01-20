import React, { useState, useEffect, useCallback } from 'react';
import './PixelArtDrawingPage.css'; // Импортируем файл стилей для стилизации, если это необходимо

const ListOfNonograms = () => {
    const [nonograms, setNonograms] = useState([]);
    const [colors] = useState(['#000000', '#FF0000', '#00FF00',
            '#0000FF', '#FFFF00', '#FF00FF', '#FFA500', '#008080',
            '#FFD1DC', '#FFB6C1', '#FF69B4', '#FF6347', '#FF4500',
            '#FFD700', '#F0E68C', '#EEE8AA', '#98FB98', '#87CEEB',
            '#FFA07A', '#FF8C00', '#FF4500', '#FFD700', '#FF69B4',
            '#FF6347', '#87CEEB', '#00BFFF', '#32CD32', '#ADFF2F',
            '#FFDAB9', '#FFE4C4', '#FFDEAD', '#F0FFF0', '#E6E6FA',
            '#D8BFD8', '#DDA0DD', '#BA55D3', '#9370DB', '#8A2BE2']);

    const handleRefresh = useCallback(() => {
        getData()
            .then(data => {
                setNonograms(data);
                for(let i = 0; i < nonograms.length; i++) {
                    console.log(
                      Object.entries(nonograms[i])
                      .map( ([key, value]) => `KEY: ${key} , VALUE: ${value}` )
                    );
                }
            })
            .catch(err => console.error(err));
    }, []);

    useEffect(() => {
        handleRefresh();
    }, [handleRefresh]);

    const cellSize = 20;
    const listGridStyles = (size) => {
        return {
            display: 'grid',
            //opacity: 0.5,
            gridTemplateColumns: `repeat(${size}, ${cellSize * (size/5)}px)`
        }
    };
    const listGridCell = (color) => {
        return {
            width: `${cellSize}px`,
            height: `${cellSize}px`,
            backgroundColor: color,
            cursor: 'pointer'
        }
    }

    return (
        <div>
            <button
                style={{
                    backgroundColor: 'white',
                    width: '60px',
                    height: '40px',
                }}
                onClick={handleRefresh}
            >
                Refresh
            </button>
            <div className="list-container">
                {nonograms.map((nonogram, index) => (
                    <div className="nonogram" key={index} style={{ display: 'flex', gap: '5px' }}>
                        <div key={index} style={{ backgroundColor: 'lightgrey', color: 'black', fontSize: '36px' }}>
                            {nonogram.id}
                        </div>
                        <div style={listGridStyles(5)}>
                            {nonogram.art.map((cell, index) => (
                                <div
                                    key={index}
                                    style={listGridCell(colors[cell])}
                                />
                            ))}
                        </div>
                        <button
                            style={{
                                backgroundColor: 'white',
                                width: '60px',
                                height: '40px',
                            }}
                            onClick={handleRefresh}>
                            Play
                        </button>
                    </div>
                ))}
            </div>
        </div>
    );
};

export default ListOfNonograms;

function getData() {
    const url = 'http://localhost:9093/api/v1/nonogram';
    let username = 'lapka';
    let password = 'zalapka';
    let auth = btoa(`${username}:${password}`);

    return fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Basic ${auth}`
        }
    })
    .then(response => response.json());
}
