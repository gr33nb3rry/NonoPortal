import React from 'react';

const Nonogram = ({ nonogram }) => {
    const { size, solution, art } = nonogram;

    if (!size || !solution || !art) {
        // Handle the case where data is not available
        return null;
    }

    // Your logic to format the nonogram data goes here
    // For simplicity, I'm just displaying the raw data

    return (
        <div className="nonogram-container">
            <div>
                <strong>ID:</strong> {nonogram.id}
            </div>
            <div>
                <strong>Size:</strong> {size}
            </div>
            <div>
                <strong>Solution:</strong> {solution.join(', ')}
            </div>
            <div>
                <strong>Art:</strong> {art.join(', ')}
            </div>
            {/* Additional styling can be applied as needed */}
        </div>
    );
};

export default Nonogram;
