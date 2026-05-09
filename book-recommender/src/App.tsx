import { useState, useCallback } from 'react'
import { Book, Genre } from './types'
import { searchBooks, getRecommendations, getBooksByGenre } from './services/booksApi'
import { BookCard } from './components/BookCard'
import { BookModal } from './components/BookModal'
import './styles.css'

const GENRES: Genre[] = [
  { label: 'Romance', query: 'romance', emoji: '💕' },
  { label: 'Fantasia', query: 'fantasy', emoji: '🧙' },
  { label: 'Ficção Científica', query: 'science fiction', emoji: '🚀' },
  { label: 'Mistério', query: 'mystery thriller', emoji: '🔍' },
  { label: 'Terror', query: 'horror', emoji: '👻' },
  { label: 'Biografia', query: 'biography', emoji: '📖' },
  { label: 'História', query: 'history', emoji: '🏛️' },
  { label: 'Autoajuda', query: 'self help', emoji: '✨' },
]

export default function App() {
  const [query, setQuery] = useState('')
  const [books, setBooks] = useState<Book[]>([])
  const [loading, setLoading] = useState(false)
  const [selectedBook, setSelectedBook] = useState<Book | null>(null)
  const [recommendations, setRecommendations] = useState<Book[]>([])
  const [recLoading, setRecLoading] = useState(false)
  const [activeGenre, setActiveGenre] = useState<string | null>(null)
  const [error, setError] = useState<string | null>(null)
  const [hasSearched, setHasSearched] = useState(false)

  const handleSearch = useCallback(async (e: React.FormEvent) => {
    e.preventDefault()
    if (!query.trim()) return
    setLoading(true)
    setError(null)
    setActiveGenre(null)
    setHasSearched(true)
    try {
      const results = await searchBooks(query)
      setBooks(results)
    } catch {
      setError('Erro ao buscar livros. Tente novamente.')
    } finally {
      setLoading(false)
    }
  }, [query])

  const handleGenre = useCallback(async (genre: Genre) => {
    setLoading(true)
    setError(null)
    setActiveGenre(genre.query)
    setQuery('')
    setHasSearched(true)
    try {
      const results = await getBooksByGenre(genre.query)
      setBooks(results)
    } catch {
      setError('Erro ao carregar gênero.')
    } finally {
      setLoading(false)
    }
  }, [])

  const handleSelectBook = useCallback(async (book: Book) => {
    setSelectedBook(book)
    setRecLoading(true)
    setRecommendations([])
    try {
      const recs = await getRecommendations(book)
      setRecommendations(recs)
    } catch {
      setRecommendations([])
    } finally {
      setRecLoading(false)
    }
  }, [])

  const handleCloseModal = useCallback(() => {
    setSelectedBook(null)
    setRecommendations([])
  }, [])

  return (
    <div className="app">
      <div className="bg-decoration" aria-hidden="true">
        <div className="bg-circle c1" />
        <div className="bg-circle c2" />
        <div className="bg-circle c3" />
      </div>

      <header className="header">
        <div className="header-inner">
          <div className="logo">
            <span className="logo-icon">📚</span>
            <span className="logo-text">Bilbo's Library</span>
          </div>
          <p className="tagline">Descubra seu próximo livro favorito</p>

          <form className="search-form" onSubmit={handleSearch}>
            <div className="search-box">
              <span className="search-icon">🔍</span>
              <input
                type="text"
                placeholder="Buscar por título, autor ou tema..."
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                className="search-input"
              />
              <button type="submit" className="search-btn" disabled={loading}>
                {loading ? <span className="spinner" /> : 'Buscar'}
              </button>
            </div>
          </form>
        </div>
      </header>

      <main className="main">
        <section className="genres-section">
          <h2 className="section-title">Explorar por gênero</h2>
          <div className="genres-grid">
            {GENRES.map((genre) => (
              <button
                key={genre.query}
                className={`genre-btn ${activeGenre === genre.query ? 'active' : ''}`}
                onClick={() => handleGenre(genre)}
              >
                <span className="genre-emoji">{genre.emoji}</span>
                <span className="genre-label">{genre.label}</span>
              </button>
            ))}
          </div>
        </section>

        {error && (
          <div className="error-msg">⚠️ {error}</div>
        )}

        {loading ? (
          <div className="books-grid">
            {Array.from({ length: 8 }).map((_, i) => (
              <div key={i} className="book-skeleton" />
            ))}
          </div>
        ) : hasSearched && books.length === 0 ? (
          <div className="empty-state">
            <span>📭</span>
            <p>Nenhum livro encontrado. Tente outra busca!</p>
          </div>
        ) : books.length > 0 ? (
          <section className="results-section">
            <h2 className="section-title">
              {activeGenre
                ? `${GENRES.find(g => g.query === activeGenre)?.emoji} ${GENRES.find(g => g.query === activeGenre)?.label}`
                : `Resultados para "${query}"`}
              <span className="results-count">{books.length} livros</span>
            </h2>
            <div className="books-grid">
              {books.map((book) => (
                <BookCard key={book.id} book={book} onClick={handleSelectBook} />
              ))}
            </div>
          </section>
        ) : (
          <div className="welcome-state">
            <div className="welcome-inner">
              <span className="welcome-icon">✨</span>
              <h2>Bem-vindo ao Bilbo's Library</h2>
              <p>Busque um livro ou escolha um gênero acima para começar a explorar!</p>
            </div>
          </div>
        )}
      </main>

      {selectedBook && (
        <BookModal
          book={selectedBook}
          recommendations={recommendations}
          loading={recLoading}
          onClose={handleCloseModal}
          onSelectBook={handleSelectBook}
        />
      )}
    </div>
  )
}
