import {createContext, useContext, useState} from 'react';
import {fetchAllNotes} from './ApiMethods';

const NoteContext = createContext();

export const useNoteContext = () => useContext(NoteContext);

export const NoteProvider = ({children}) => {
    const [notes, setNotes] = useState([]);

    const getNotes = async () => {
        const response = await fetchAllNotes();
        setNotes(response.data);
    };

    return (
        <NoteContext.Provider value={{notes, setNotes, getNotes}}>
            {children}
        </NoteContext.Provider>
    );
};