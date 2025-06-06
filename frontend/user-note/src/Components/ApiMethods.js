import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/user-note';

const getAuthHeaders = () => {
    const token = sessionStorage.getItem("token");
    return {
        headers: {
            Authorization: `Basic ${token}`,
            'Content-Type': 'application/json'
        }
    };
};

export const fetchAllNotes = async() => {
    try {
        return await axios.get(`${API_BASE_URL}/home`, getAuthHeaders());
    }
    catch(error) {
        if (error.response.status === 403) {
           return {status: 403, data: "** You don't have the permission to view the note **"};
        }
    }
};

export const addNote = async(newNote) => {
    try {
        return await axios.post(`${API_BASE_URL}/home`, newNote, getAuthHeaders());
    }
    catch(error) {
        if (error.response.status === 403) {
           return {status: 403, data: "** You don't have the permission to add the note **"};
        }
        else if (error.response.status === 400) {
           return {status: 400, data: error.response.data};
        }
    }
};

export const updateNote = async(updatedNote) => {
    try {
        return await axios.put(`${API_BASE_URL}/home/${updatedNote.id}`, updatedNote, getAuthHeaders());
    }
    catch(error) {
        if (error.response.status === 403) {
             return {status: 403, data: "** You don't have the permission to update the note **"};
        }
        else if (error.response.status === 400) {
            return {status: 400, data: error.response.data};
        }
    }
};

export const deleteNoteById = async(noteId) => {
    try {
        return await axios.delete(`${API_BASE_URL}/home/${noteId}`, getAuthHeaders());
    }
    catch(error) {
        if (error.response.status === 403) {
            return {status: 403, data: "** You don't have the permission to delete the note **"};
        }
    }

};

export const authUser = async(credentials) => {
    try {
        return await axios.post(`http://localhost:8080/user/login`, credentials);
    }
    catch(error) {
        if (error.response.status === 400) {
            return {status: 400, data: error.response.data};
        }
    }
}


