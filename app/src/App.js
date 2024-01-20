import React from 'react';
import PixelArtDrawingPage from './PixelArtDrawingPage';
import ListOfNonograms from './ListOfNonograms';

function App() {
    return (
        <div className="App">
            <h1>CREATE</h1>
            <PixelArtDrawingPage />
            <h1>LIST</h1>
            <ListOfNonograms />
        </div>
    );
}

export default App;
